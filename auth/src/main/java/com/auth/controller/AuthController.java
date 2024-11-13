package com.auth.controller;

import com.auth.bom.AccountAuthenticationForm;
import com.auth.bom.AccountRegistrationForm;
import com.auth.bom.AccountResponseLogin;
import com.auth.bom.AccountResponseRegister;
import com.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<AccountResponseRegister> registerUser(@RequestBody AccountRegistrationForm registrationForm) {
        AccountResponseRegister response = authService.registerUser(registrationForm);
        return ResponseEntity.ok(response);
    }

    // Аутентификация пользователя
    @PostMapping("/authenticateUser")
    public ResponseEntity<AccountResponseLogin> authenticateUser(@RequestBody AccountAuthenticationForm authenticationForm) {
        AccountResponseLogin response = authService.authenticateUser(authenticationForm);
        return ResponseEntity.ok(response);
    }
}
