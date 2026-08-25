package com.example.Subscription.service;

import com.example.Subscription.mapper.SubscriptionMapper;
import com.example.Subscription.model.dto.SubscriptionCacheDto;
import com.example.Subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCacheDto getSubscription(String login) {
        if (login != null) {
            log.info("Check login {} exists to DB", login);
            SubscriptionCacheDto subscriptionCacheDto = SubscriptionMapper
                    .toSubscriptionCacheDto(subscriptionRepository.findByLogin(login));
            log.info("Get Subscription CacheDto {}", subscriptionCacheDto);
            return subscriptionCacheDto;
        }
        return null;
    }
}
