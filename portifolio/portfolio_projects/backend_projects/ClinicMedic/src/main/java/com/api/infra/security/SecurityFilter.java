package com.api.infra.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.api.domain.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter responsible for intercepting HTTP requests to validate JWT tokens.
 * It populates the SecurityContext if a valid token is found.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    /**
     * Internal filter logic to extract, validate and authenticate the user based on
     * JWT.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Extract the token from the 'Authorization' header
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            // 2. Validate token and retrieve the subject (user email)
            var subject = tokenService.getSubject(tokenJWT);

            // 3. Fetch user details from database
            var user = userService.findByEmail(subject);

            // 4. Create authentication object for Spring Security
            var authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities());

            // 5. Set authentication in the security context for the duration of the request
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Proceed to the next filter in the chain
        filterChain.doFilter(request, response);
    }

    /**
     * Helper method to parse the 'Bearer' token from the Authorization header.
     */
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Cleaner way to remove "Bearer "
        }

        return null;
    }
}