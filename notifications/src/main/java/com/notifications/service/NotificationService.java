package com.notifications.service;

import com.notifications.bom.UserBom;
import com.notifications.connector.AuthConnector;
import com.notifications.model.Notification;
import com.notifications.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AuthConnector authConnector;

    public List<Notification> getNotifications(String token) {
        UserBom userBom = authConnector.getCurrentUser(token);
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userBom.getId());
    }
}
