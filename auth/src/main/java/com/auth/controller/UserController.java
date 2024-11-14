package com.auth.controller;

import com.auth.bom.UserProfileResponse;
import com.auth.exception.SubscriptionAlreadyExistsException;
import com.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Long id) {
        UserProfileResponse response = userService.getUserProfile(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<String> followUser(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            userService.followUser(token, id);
            return ResponseEntity.ok("Вы успешно подписались на пользователя с ID " + id);
        } catch (SubscriptionAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            userService.unfollowUser(token, id);
            return ResponseEntity.ok("Вы успешно отписались от пользователя с ID " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
