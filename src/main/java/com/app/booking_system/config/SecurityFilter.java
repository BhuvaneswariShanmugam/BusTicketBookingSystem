package com.app.booking_system.config;

import com.app.booking_system.entity.UsersCredential;
import com.app.booking_system.repository.UsersCredentialRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final UsersCredentialRepository usersRepository;

    public SecurityFilter(TokenProvider tokenProvider, UsersCredentialRepository usersRepository) {
        this.tokenProvider = tokenProvider;
        this.usersRepository = usersRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = recoverToken(request);
            if (token != null) {
                String email = tokenProvider.validateToken(token);

                UsersCredential user = usersRepository.findByEmail(email)

                        .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Error during authentication filter processing", ex);
        }
        filterChain.doFilter(request, response);
    }



    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }
}