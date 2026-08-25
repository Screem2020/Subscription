package com.example.Subscription.service;

import com.example.Subscription.enums.FileRunSubscription;
import com.example.Subscription.enums.StatusEvent;
import com.example.Subscription.model.dto.SubscriptionCacheDto;
import com.example.Subscription.model.entity.OutboxTable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class ProcessService {
    private final SubscriptionService subscriptionService;
    private final ObjectMapper objectMapper;
    private final OutboxManager outboxManager;

    public void process(String login) throws JsonProcessingException {
        SubscriptionCacheDto subscriptionDto = subscriptionService.getSubscription(login);
        UUID uuid = UUID.randomUUID();
        try {
            String payload = objectMapper.writeValueAsString(subscriptionDto);
            log.info("Check time subscription found");
            OutboxTable outboxTable = new OutboxTable(
                    uuid,
                    payload,
                    0,
                    null,
                    FileRunSubscription.RUN,
                    StatusEvent.UPLOAD,
                    null);
            outboxManager.checkTimeSubscription();
            outboxManager.save(outboxTable);
        } catch (Exception e) {
            log.error("Error processing outbox table", e);
            String payload = objectMapper.writeValueAsString(subscriptionDto);
            OutboxTable outboxTable = new OutboxTable(
                    uuid,
                    payload,
                    0,
                    null,
                    FileRunSubscription.RUN,
                    StatusEvent.FAILED,
                    null);
            outboxManager.save(outboxTable);
        }
    }
}
