package com.kiranaservices.kirana_transactions.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RateLimitingConfig {

    private final Map<String, Bucket> ipBuckets = new ConcurrentHashMap<>();

    @Bean
    public OncePerRequestFilter rateLimitingFilter() {
        return new OncePerRequestFilter() {

            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {

                String ipAddress = request.getRemoteAddr(); // Retrieve the client's IP address

                Bucket bucket = ipBuckets.computeIfAbsent(ipAddress, this::createNewBucket);

                // Check if the bucket allows the request (rate limit)
                if (bucket.tryConsume(1)) {
                    filterChain.doFilter(request, response);
                } else {
                    response.setStatus(429);
                    response.getWriter().write("Too Many Requests - Rate limit exceeded");
                }
            }

            private Bucket createNewBucket(String ipAddress) {
                Refill refill = Refill.intervally(10, Duration.ofMinutes(1));
                // Refill 10 tokens every 1 minute
                Bandwidth limit = Bandwidth.classic(10, refill);
                return Bucket4j.builder().addLimit(limit).build(); // Build and return the bucket
            }
        };
    }
}
