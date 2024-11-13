package com.auth.service;

import com.auth.bom.AccountAuthenticationForm;
import com.auth.bom.AccountRegistrationForm;
import com.auth.bom.AccountResponseLogin;
import com.auth.bom.AccountResponseRegister;
import com.auth.model.User;
import com.auth.repository.UserRepository;
import com.auth.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtils jwtTokenUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    // Регистрация пользователя
    public AccountResponseRegister registerUser(AccountRegistrationForm registrationForm) {
        // Проверка на существование email
        if (userRepository.existsByEmail(registrationForm.getEmail())) {
            throw new RuntimeException("Email уже занят");
        }

        // Создание нового пользователя без картинки профиля
        User newUser = new User();
        newUser.setUsername(registrationForm.getUsername());
        newUser.setEmail(registrationForm.getEmail());
        newUser.setPasswordHash(passwordEncoder.encode(registrationForm.getPassword()));

        User savedUser = userRepository.save(newUser);

        // Здесь не генерируем токен, только возвращаем данные пользователя
        return getAccountResponseWithoutToken(savedUser);
    }

    // Метод без токена для регистрации
    private AccountResponseRegister getAccountResponseWithoutToken(User savedUser) {
        AccountResponseRegister accountResponse = new AccountResponseRegister();
        accountResponse.setId(savedUser.getId());
        accountResponse.setUsername(savedUser.getUsername());
        accountResponse.setEmail(savedUser.getEmail());

        // Токен не генерируется
        return accountResponse;
    }

    // Аутентификация пользователя
    public AccountResponseLogin authenticateUser(AccountAuthenticationForm authenticationForm) {
        User user = userRepository.findByEmail(authenticationForm.getEmail())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        // Проверка пароля
        if (!passwordEncoder.matches(authenticationForm.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Неверный пароль");
        }

        // Генерация JWT токена
        return getAccountResponseWithToken(user);
    }

    // Метод с токеном для аутентификации
    private AccountResponseLogin getAccountResponseWithToken(User user) {
        String token = jwtTokenUtils.generateToken(user);

        AccountResponseLogin accountResponse = new AccountResponseLogin();
        accountResponse.setId(user.getId());
        accountResponse.setUsername(user.getUsername());
        accountResponse.setEmail(user.getEmail());
        accountResponse.setToken(token); // Генерация и добавление токена

        return accountResponse;
    }
}
