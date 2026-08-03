package com.example.Subscription.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionCacheDto {
    private String login;
    private String subscriptionType;
    private Instant expiresAt;
}
