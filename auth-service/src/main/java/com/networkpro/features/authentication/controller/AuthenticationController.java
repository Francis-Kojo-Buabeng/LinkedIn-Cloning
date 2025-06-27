
package com.networkpro.features.authentication.controller;

import com.networkpro.features.authentication.dto.AuthenticationRequestBody;
import com.networkpro.features.authentication.dto.AuthenticationResponseBody;
import com.networkpro.features.authentication.model.AuthenticationUser;
import com.networkpro.features.authentication.service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/user")
    public AuthenticationUser getUser(@RequestAttribute("authenticationUser") AuthenticationUser authenticationUser) {
        return authenticationUser;
    }

    @PostMapping("/register")
    public AuthenticationResponseBody registerPage(@Valid @RequestBody AuthenticationRequestBody registerRequestBody)
            throws MessagingException, UnsupportedEncodingException {
        return authenticationService.register(registerRequestBody);
    }

    @PostMapping("/login")
    public AuthenticationResponseBody loginPage(@Valid @RequestBody AuthenticationRequestBody loginRequestBody) {
        return authenticationService.login(loginRequestBody);
    }

    @PutMapping("/validate-email-verification-token")
    public String verifyEmail(@RequestParam String token, @RequestAttribute("authenticationUser") AuthenticationUser user) {
        authenticationService.validateEmailVerificationToken(token, user.getEmail());
        return "Email verified successfully";
    }

    @GetMapping("/send-email-verification-token")
    public String sendEmailVerificationToken(@RequestAttribute("authenticationUser") AuthenticationUser user) {
        authenticationService.sendEmailVerificationToken(user.getEmail());
        return "Email verification sent successfully";
    }

    @PutMapping("/send-password-reset-token")
    public String sendPasswordResetToken(@RequestParam String email) {
        authenticationService.sendPasswordResetToken(email);
        return "Password reset sent successfully";
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String newPassword, @RequestParam String token, @RequestParam String email) {
        authenticationService.resetPassword(email, newPassword, token );
        return "Password reset successfully";
    }
}
