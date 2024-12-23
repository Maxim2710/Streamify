package com.auth.controller;

import com.auth.bom.*;
import com.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registerUser")
    public ResponseEntity<Object> registerUser(@RequestBody AccountRegistrationForm registrationForm) {
        try {
            AccountResponseRegister response = authService.registerUser(registrationForm);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(ex.getMessage()));
        }
    }


    @PostMapping("/authenticateUser")
    public ResponseEntity<AccountResponseLogin> authenticateUser(@RequestBody AccountAuthenticationForm authenticationForm) {
        AccountResponseLogin response = authService.authenticateUser(authenticationForm);
        return ResponseEntity.ok(response);
    }
}
