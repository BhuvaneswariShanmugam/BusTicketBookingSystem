package com.app.booking_system.config;

import com.app.booking_system.entity.UserCredential;
import com.app.booking_system.exception.InvalidTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.security.core.userdetails.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TokenProvider {

    @Value("${security.jwt.secret-key}")
    private String JWT_SECRET;

//    public String generateAccessToken(UserCredential user) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
//            return JWT.create().withSubject(user.getUsername()).withClaim("username", user.getUsername())
//                    .withExpiresAt(genAccessExpirationDate()).sign(algorithm);
//        } catch (JWTCreationException exception) {
//            throw new JWTCreationException("Error while generating token", exception);
//        }
//    }

    public String generateAccessToken(UserCredential user) {
        try {
            String username = user.getUsername();
            String firstName = user.getFirstName(); // Get the user's first name from UserCredential

            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                    .withSubject(username)
                    .withClaim("UserEmail", username)
                    .withIssuedAt(Instant.now()) // Add issuedAt claim
                    .withExpiresAt(genAccessExpirationDate())
                    .withClaim("Name", firstName) // Add the firstName claim here
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating access token", exception);
        }
    }





    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token", exception);
        }
    }

    public String refreshAccessToken(String refreshToken) {
        String email = validateRefreshToken(refreshToken);
        UserCredential user = new UserCredential(null, email, null, null,null);
        return generateAccessToken(user);
    }

    public String generateRefreshToken(UserCredential user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create().withSubject(user.getUsername()).withClaim("username", user.getUsername())
                    .withExpiresAt(genRefreshExpirationDate()).sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating refresh token", exception);
        }
    }

    public String validateRefreshToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating refresh token", exception);
        }
    }

    private Instant genAccessExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant genRefreshExpirationDate() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC);
    }


}