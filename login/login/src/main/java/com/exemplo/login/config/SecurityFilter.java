package com.exemplo.login.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    public SecurityFilter(JwtConfig jwtConfig){
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        System.out.println("========== FILTRO ==========");

        String authrizerHeader = request.getHeader("Authorization");

        System.out.println("HEADER: " + authrizerHeader);

        if (Strings.isNotEmpty(authrizerHeader)
                && authrizerHeader.startsWith("Bearer ")) {

            String token = authrizerHeader.substring("Bearer ".length());

            System.out.println("TOKEN: " + token);

            Optional<JwtUserData> optUser = jwtConfig.validateToken(token);

            System.out.println("TOKEN VALIDO: " + optUser.isPresent());

            if (optUser.isPresent()) {

                JwtUserData userData = optUser.get();

                System.out.println("USER ID: " + userData.userId());
                System.out.println("USER EMAIL: " + userData.email());

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userData,
                                null,
                                null
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

                System.out.println("AUTENTICACAO CRIADA");
            }
        }

        filterChain.doFilter(request, response);
    }
}
