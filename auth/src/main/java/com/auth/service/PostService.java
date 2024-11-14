package com.auth.service;

import com.auth.bom.UserBom;
import com.auth.converter.UserConverter;
import com.auth.model.User;
import com.auth.repository.UserRepository;
import com.auth.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserRepository userRepository;

    public UserBom getCurrentUser(String token) {
        try {
            Long currentUserId = jwtTokenUtils.getUserIdFromToken(token);

            Optional<User> currentUser = userRepository.findById(currentUserId);

            if (currentUser.isEmpty()) {
                throw new RuntimeException("Пользователь не найден");
            }

            return UserConverter.convertToBom(currentUser.get());
        } catch (Exception e) {
            throw new RuntimeException("При получении текущего пользователя произошла ошибка", e);
        }
    }
}
