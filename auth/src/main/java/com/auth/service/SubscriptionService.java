package com.auth.service;

import com.auth.repository.SubscriptionRepository;
import com.auth.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Long> getSubscriptions(String token) {
        Long currentUserId = jwtTokenUtils.getUserIdFromToken(token);

        return subscriptionRepository.findFollowingIdsByUserId(currentUserId);
    }
}
