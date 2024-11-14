package com.auth.service;

import com.auth.bom.*;
import com.auth.model.User;
import com.auth.repository.UserRepository;
import com.auth.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    public AccountResponseRegister registerUser(AccountRegistrationForm registrationForm) {
        if (userRepository.existsByEmail(registrationForm.getEmail())) {
            throw new RuntimeException("Email уже занят");
        }

        User newUser = new User();
        newUser.setUsername(registrationForm.getUsername());
        newUser.setEmail(registrationForm.getEmail());
        newUser.setPasswordHash(passwordEncoder.encode(registrationForm.getPassword()));

        User savedUser = userRepository.save(newUser);

        return getAccountResponseWithoutToken(savedUser);
    }

    private AccountResponseRegister getAccountResponseWithoutToken(User savedUser) {
        AccountResponseRegister accountResponse = new AccountResponseRegister();
        accountResponse.setId(savedUser.getId());
        accountResponse.setUsername(savedUser.getUsername());
        accountResponse.setEmail(savedUser.getEmail());

        return accountResponse;
    }

    public AccountResponseLogin authenticateUser(AccountAuthenticationForm authenticationForm) {
        User user = userRepository.findByEmail(authenticationForm.getEmail())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!passwordEncoder.matches(authenticationForm.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Неверный пароль");
        }

        return getAccountResponseWithToken(user);
    }

    private AccountResponseLogin getAccountResponseWithToken(User user) {
        String token = jwtTokenUtils.generateToken(user);

        AccountResponseLogin accountResponse = new AccountResponseLogin();
        accountResponse.setId(user.getId());
        accountResponse.setUsername(user.getUsername());
        accountResponse.setEmail(user.getEmail());
        accountResponse.setToken(token);

        return accountResponse;
    }

    public UserProfileResponse getUserProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        UserProfileResponse userProfile = new UserProfileResponse();
        userProfile.setId(user.getId());
        userProfile.setUsername(user.getUsername());
        userProfile.setEmail(user.getEmail());

        return userProfile;
    }
}
