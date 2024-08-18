package com.example.server.infra.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.server.domain.dto.LoginDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().startsWith("/login")) {
            filterChain.doFilter(request, response);
        } else {
            String token = request.getHeader("Authorization").substring(7);
            Algorithm algorithm = Algorithm.HMAC256("./w3r");
            DecodedJWT decodedJWT;
            try {
                JWTVerifier verifier = JWT.require(algorithm)
                        // specify any specific claim validations
                        .withIssuer("test-key-secret")
                        // reusable verifier instance
                        .build();
                decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                System.out.println(username);
                System.out.println("Subject ");
                List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
                if (roles == null) {
                    roles = Collections.emptyList();
                }

                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null,authorities);

                //SecurityContextHolder.getContext().setAuthentication(authentication);
                //Debug
                response.sendError(404);
                filterChain.doFilter(request, response);
            } catch (JWTVerificationException exception){
                System.out.println("Teste");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }
}
