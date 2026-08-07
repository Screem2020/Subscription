package com.example.Subscription.model.dto;

import com.example.Subscription.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionCacheDto {
    private String login;
    private SubscriptionType subscriptionType;
    private Instant expiresAt;
}
