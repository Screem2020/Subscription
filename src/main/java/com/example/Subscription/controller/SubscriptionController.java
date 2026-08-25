package com.example.Subscription.controller;

import com.example.Subscription.model.dto.SubscriptionCacheDto;
import com.example.Subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping("{login}")
    public SubscriptionCacheDto getLoginSubscription(@PathVariable ("login")  String login) {
        return subscriptionService.getSubscription(login);
    }
}
