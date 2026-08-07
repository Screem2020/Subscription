package com.example.Subscription.service;

import com.example.Subscription.enums.SubscriptionType;
import com.example.Subscription.model.entity.SubscriptionEntity;
import com.example.Subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SchedulerCheckSubscrip {
    private final SubscriptionUpload subscriptionUpload;

    @Transactional
    @Scheduled(fixedRateString = "${scheduler.fixed-rate}")
    @SchedulerLock(
            name = "TaskSchedulerSubscription",
            lockAtLeastFor = "PT1M",
            lockAtMostFor = "PT5M"
    )
    public void SchedulerJob() {
        try {
            System.out.println("Scheduler is running");
            List<SubscriptionEntity> page = subscriptionUpload.page();
            page.stream()
                    .peek(subscriptionEntity -> subscriptionEntity.setSubscriptionType(SubscriptionType.FREE));
        } catch (Exception e){

        }
    }
}
