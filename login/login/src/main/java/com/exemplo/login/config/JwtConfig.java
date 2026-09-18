package com.exemplo.login.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exemplo.login.entites.User;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtConfig {

    private String secret = "secretTest";

    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("userId", user.getId())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(8000))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

}
