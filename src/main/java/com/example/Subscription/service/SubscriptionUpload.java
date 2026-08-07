package com.example.Subscription.service;

import com.example.Subscription.enums.SubscriptionType;
import com.example.Subscription.model.entity.SubscriptionEntity;
import com.example.Subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SubscriptionUpload {

    private final SubscriptionRepository subscriptionRepository;

    public List<SubscriptionEntity> page() {
        Pageable pageable = PageRequest.of(0, 100);
        return subscriptionRepository.findBySubscriptionType(SubscriptionType.PREMIUM, pageable)
                .stream()
                .filter(entity -> Instant.now().isAfter(entity.getExpiresAt()))
                .toList();
    }
}
