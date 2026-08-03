package com.example.Subscription.service;

import com.example.Subscription.mapper.SubscriptionMapper;
import com.example.Subscription.model.dto.SubscriptionCacheDto;
import com.example.Subscription.model.entity.SubscriptionEntity;
import com.example.Subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    public SubscriptionEntity save(SubscriptionEntity subscriptionEntity) {
        return  subscriptionRepository.save(subscriptionEntity);
    }

    public SubscriptionCacheDto getSubscription(String login) {
        return SubscriptionMapper.toSubscriptionCacheDto(subscriptionRepository.findByLogin(login));
    }
}
