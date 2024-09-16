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

    // Store buckets for each IP address in a thread-safe map
    private final Map<String, Bucket> ipBuckets = new ConcurrentHashMap<>();

    @Bean
    public OncePerRequestFilter rateLimitingFilter() {
        return new OncePerRequestFilter() {

            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {

                String ipAddress = request.getRemoteAddr(); // Retrieve the client's IP address

                // Fetch or create a rate limiting bucket for the IP
                Bucket bucket = ipBuckets.computeIfAbsent(ipAddress, this::createNewBucket);

                // Check if the bucket allows the request (rate limit)
                if (bucket.tryConsume(1)) {
                    filterChain.doFilter(request, response); // If allowed, process the request
                } else {
                    response.setStatus(429); // Return HTTP status code 429 (Too Many Requests)
                    response.getWriter().write("Too Many Requests - Rate limit exceeded");
                }
            }

            // Define a bucket for rate limiting (e.g., 10 requests per minute)
            private Bucket createNewBucket(String ipAddress) {
                Refill refill = Refill.intervally(10, Duration.ofMinutes(1)); // Refill 10 tokens every 1 minute
                Bandwidth limit = Bandwidth.classic(10, refill); // Set the bandwidth limit to 10 requests/minute
                return Bucket4j.builder().addLimit(limit).build(); // Build and return the bucket
            }
        };
    }
}
