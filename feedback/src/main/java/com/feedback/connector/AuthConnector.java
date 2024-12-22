package com.feedback.connector;

import com.feedback.bom.UserBom;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-connector", url = "${connector.auth.url}")
public interface AuthConnector {
    @GetMapping(path = "/getCurrentUser")
    UserBom getCurrentUser(@RequestHeader(name = "Authorization") String token);
}
