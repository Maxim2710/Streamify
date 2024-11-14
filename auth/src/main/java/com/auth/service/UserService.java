package com.auth.service;

import com.auth.bom.UserProfileResponse;
import com.auth.exception.SubscriptionAlreadyExistsException;
import com.auth.model.Subscription;
import com.auth.model.User;
import com.auth.repository.SubscriptionRepository;
import com.auth.repository.UserRepository;
import com.auth.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    public UserProfileResponse getUserProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        UserProfileResponse userProfile = new UserProfileResponse();
        userProfile.setId(user.getId());
        userProfile.setUsername(user.getUsername());
        userProfile.setEmail(user.getEmail());

        return userProfile;
    }

    public void followUser(String token, Long followingId) {
        Long userId = jwtTokenUtils.getUserIdFromToken(token);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        User following = userRepository.findById(followingId).orElseThrow(() -> new RuntimeException("Пользователь для подписки не найден"));

        if (subscriptionRepository.existsByUserAndFollowing(user, following)) {
            throw new SubscriptionAlreadyExistsException("Вы уже подписаны на этого пользователя");
        }

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setFollowing(following);
        subscriptionRepository.save(subscription);
    }

    public void unfollowUser(String token, Long followingId) {
        Long userId = jwtTokenUtils.getUserIdFromToken(token);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        User following = userRepository.findById(followingId).orElseThrow(() -> new RuntimeException("Пользователь для отписки не найден"));

        Optional<Subscription> subscriptionOpt = subscriptionRepository.findByUserAndFollowing(user, following);
        if (subscriptionOpt.isEmpty()) {
            throw new RuntimeException("Вы не подписаны на этого пользователя");
        }

        subscriptionRepository.delete(subscriptionOpt.get());
    }

}
