package com.example.Subscription.repository;

import com.example.Subscription.model.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, String> {
    SubscriptionEntity findByLogin(String login);
}
