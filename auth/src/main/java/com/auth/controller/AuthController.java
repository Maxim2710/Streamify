package com.auth.controller;

import com.auth.bom.*;
import com.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registerUser")
    public ResponseEntity<AccountResponseRegister> registerUser(@RequestBody AccountRegistrationForm registrationForm) {
        AccountResponseRegister response = authService.registerUser(registrationForm);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticateUser")
    public ResponseEntity<AccountResponseLogin> authenticateUser(@RequestBody AccountAuthenticationForm authenticationForm) {
        AccountResponseLogin response = authService.authenticateUser(authenticationForm);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Long id) {
        UserProfileResponse response = authService.getUserProfile(id);
        return ResponseEntity.ok(response);
    }
}
