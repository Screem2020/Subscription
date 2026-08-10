package com.example.Subscription.service;

import com.example.Subscription.enums.SubscriptionType;
import com.example.Subscription.model.entity.SubscriptionEntity;
import com.example.Subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriptionExpirationScheduler {
    private final SubscriptionRepository subscriptionRepository;
    private final ProcessService processService;

    @Transactional
    @Scheduled(fixedRateString = "${scheduler.subscription-expiration-rate}")
    public void checkExpiredSubscriptions() {
        try {
            Pageable pageable = PageRequest.of(0, 100);
            var bySubscriptionTypeAndExpiresAtBefore = subscriptionRepository
                    .findBySubscriptionTypeAndExpiresAtBefore(SubscriptionType.PREMIUM, Instant.now(), pageable);
            if (bySubscriptionTypeAndExpiresAtBefore.getTotalElements() == 0) {
                log.info("Subscription expired");
                return;
            }
            for (SubscriptionEntity subscriptionEntity : bySubscriptionTypeAndExpiresAtBefore) {
                if (Instant.now().isAfter(subscriptionEntity.getExpiresAt())) {
                    subscriptionEntity.setSubscriptionType(SubscriptionType.FREE);
                    processService.process(subscriptionEntity.getLogin());
                }
            }
        } catch (Exception e) {
            log.info("Error scheduler change type subscription", e);
        }
    }
}
