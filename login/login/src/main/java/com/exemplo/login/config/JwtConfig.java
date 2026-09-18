package com.exemplo.login.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.exemplo.login.entites.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Component
public class JwtConfig {

    private String secret = "secretTest";

    public String generateToken(UserDetailsImp userDetailsImp){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("userId", userDetailsImp.getUseId())
                .withSubject(userDetailsImp.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(8000))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JwtUserData> validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build().verify(token);

            return Optional.of(
                    new JwtUserData(
                            decodedJWT.getClaim("userId").asLong(),
                            decodedJWT.getSubject()
                    )
            );

        } catch (JWTVerificationException e){
            return Optional.empty();
        }
    }

}
