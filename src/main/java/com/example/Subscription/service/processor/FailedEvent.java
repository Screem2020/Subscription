package com.example.Subscription.service.processor;

import com.example.Subscription.config.KafkaTopicConfig;
import com.example.Subscription.kafka.ProducerEvent;
import com.example.Subscription.model.entity.OutboxTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FailedEvent implements EventProcessor {
    private final ProducerEvent producerEvent;
    private final KafkaTopicConfig kafkaTopicConfig;

    @Override
    public void execute(OutboxTable outboxTable) {
        producerEvent.sendFiledSubscription(
                kafkaTopicConfig.getFileFailedSubscription(),
                outboxTable.getEventId(),
                outboxTable.getPayload());
    }
}
