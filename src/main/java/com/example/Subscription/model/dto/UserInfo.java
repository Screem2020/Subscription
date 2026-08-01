package com.example.Subscription.model.dto;

import java.time.Instant;

public record UserInfo (
     String loginKey,
     String typeSubscription,
     Instant timeToLive) {
}
