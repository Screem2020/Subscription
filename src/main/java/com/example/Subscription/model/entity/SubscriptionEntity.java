package com.example.Subscription.model.entity;

import com.example.Subscription.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscription_entity")
public class SubscriptionEntity {
    @Id
    private UUID eventId;
    private String login;
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;
    private Instant expiresAt;
}
