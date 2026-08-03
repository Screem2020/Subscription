package com.example.Subscription.mapper;

import com.example.Subscription.model.dto.SubscriptionCacheDto;
import com.example.Subscription.model.entity.SubscriptionEntity;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class SubscriptionMapper {

    public static SubscriptionCacheDto toSubscriptionCacheDto(SubscriptionEntity subscriptionEntity) {
        log.info("SubscriptionMapper toSubscriptionCacheDto");
        return new SubscriptionCacheDto(
                subscriptionEntity.getLogin(),
                subscriptionEntity.getSubscriptionType(),
                subscriptionEntity.getExpiresAt());
    }

    public static SubscriptionEntity toSubscriptionEntity(SubscriptionCacheDto subscriptionCacheDto) {
        log.info("SubscriptionMapper toSubscriptionEntity");
        return new SubscriptionEntity(
                subscriptionCacheDto.getLogin(),
                subscriptionCacheDto.getSubscriptionType(),
                subscriptionCacheDto.getExpiresAt());
    }
}
