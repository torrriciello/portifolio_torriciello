package com.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Main security configuration class.
 * Defines access rules, authentication mechanisms, and filter chains.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Autowired
        private SecurityFilter securityFilter;

        /**
         * Configures the security filter chain.
         * Sets stateless session policy, disables CSRF (not needed for JWT),
         * and defines public/private endpoints.
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                return http
                                .csrf(csrf -> csrf.disable()) // Disabled as we are using JWT, not cookies
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                // Public endpoints: Registration and Login
                                                .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()

                                                // Swagger/OpenAPI documentation (accessible for developers)
                                                .requestMatchers(
                                                                "/v3/api-docs/**",
                                                                "/swagger-ui.html",
                                                                "/swagger-ui/**")
                                                .permitAll()

                                                // Generic public assets
                                                .requestMatchers("/public/**").permitAll()

                                                // All other requests must be authenticated
                                                .anyRequest().authenticated())
                                // Adds our custom JWT filter before the standard username/password filter
                                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

        /**
         * Exposes the AuthenticationManager as a Bean so it can be used
         * in the AuthController to perform programmatic login.
         */
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
        }
}