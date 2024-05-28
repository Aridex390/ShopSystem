package com.oop2.backend.user.config;

import com.oop2.backend.user.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This Class is a component, validate the JWT token and updates the security context holder.
 *
 * @author Florian Reining
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    /**
     * Dependency  to @{@link com.oop2.backend.user.service.JwtService}
     */
    private final JwtService jwtService;
    /**
     * Dependency  to @{@link UserDetailsService}
     */
    private final UserDetailsService userDetailsService;

    /**
     * The methode filters the JWT token. First the request head is checked if there is a JWT Token.
     * Then all details will extract to check if the JWT token is for the user.
     * After that the methode calls the validateService methode of @{@link JwtService} and validate the rest of the Token.
     *
     * @param request  takes the request send from the frontend
     * @param response takes the respons of the request
     * @param chain    takes a FilterChain.
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Defines a general input output exception.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain chain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String email;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        token = authHeader.substring(7);
        email = jwtService.extractEmail(token);
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        email,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }

}