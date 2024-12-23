package com.feed.connector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "subscriptions-connector", url = "${connector.subscriptions.url}")
public interface SubscriptionsConnector {

    @GetMapping("/subscriptions")
    List<Long> getSubscriptions(@RequestHeader(name = "Authorization") String token);
}
