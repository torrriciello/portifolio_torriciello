package com.api.domain.user.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.domain.user.UserService;

/**
 * Service responsible for bridging Spring Security with the application's user
 * database.
 * It implements the UserDetailsService interface to provide authentication
 * data.
 */
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * Locates the user based on the email provided during login.
     * Spring Security calls this method to verify credentials.
     * * @param email The identifier for the user (in this case, the email).
     * 
     * @return UserDetails implementation (your User entity).
     * @throws UsernameNotFoundException if no user is found with the provided
     *                                   email.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = userService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return user;
    }
}