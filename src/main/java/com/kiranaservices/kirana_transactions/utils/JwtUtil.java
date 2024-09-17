package com.kiranaservices.kirana_transactions.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "secret-key";
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET.getBytes()));
    }

    public String validateToken(String token) {
        System.out.println("Request reached for validating the token.... in validateToken");
        System.out.println(token);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET.getBytes())).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        System.out.println("Decoded token giving the subject which is the userMail.....");
        System.out.println(decodedJWT.getSubject());
        return decodedJWT.getSubject(); // Returns the username if valid
    }
}