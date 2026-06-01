package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.api.domain.user.User;
import com.api.domain.user.UserService;
import com.api.domain.user.auth.LoginData;
import com.api.domain.user.auth.PasswordResetData;
import com.api.domain.user.auth.PasswordResetRequest;
import com.api.infra.security.TokenJWTData;
import com.api.infra.security.TokenService;
import jakarta.validation.Valid;

/**
 * Controller responsible for handling authentication-related requests.
 */
@CrossOrigin(origins = "*") // Allows requests from any origin (Update this for production security)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    /**
     * Authenticates a user and returns a JWT token.
     * * @param login Data transfer object containing user credentials (email and
     * password).
     * 
     * @return ResponseEntity containing the generated JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginData login) {
        // Creates a token object with the provided credentials for Spring Security to
        // process
        var authenticationToken = new UsernamePasswordAuthenticationToken(login.email(), login.password());

        // Triggers the authentication process; throws an exception if credentials are
        // invalid
        var authentication = manager.authenticate(authenticationToken);

        // Generates the JWT token for the successfully authenticated user
        var token = tokenService.gerarToken((User) authentication.getPrincipal());

        // Returns the token wrapped in a DTO with an HTTP 200 OK status
        return ResponseEntity.ok(new TokenJWTData(token));
    }

    /**
     * Endpoint to request password recovery.
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid PasswordResetRequest request) {

        userService.generatePasswordResetToken(request.email());

        return ResponseEntity.ok("Recovery email sent");
    }

    /**
     * Endpoint to reset the password using a token.
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid PasswordResetData data) {

        userService.resetPassword(data.token(), data.newPassword());

        return ResponseEntity.ok("Password updated successfully");
    }

    /**
     * Logs out the authenticated user.
     * 
     * Since the application uses stateless JWT authentication, the backend
     * does not invalidate the token. The logout process is handled on the
     * client side by removing the stored JWT token.
     * 
     * @return ResponseEntity confirming that the logout request was processed.
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logout successful");
    }
}