package com.example.Subscription.repository;

import com.example.Subscription.enums.SubscriptionType;
import com.example.Subscription.model.entity.SubscriptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, SubscriptionType> {
    SubscriptionEntity findByLogin(String login);
    Page<SubscriptionEntity> findBySubscriptionType(SubscriptionType subscriptionType, Pageable pageable);
}
