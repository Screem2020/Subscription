package com.example.Subscription.service.processor;

import com.example.Subscription.config.KafkaTopicConfig;
import com.example.Subscription.kafka.ProducerEvent;
import com.example.Subscription.model.entity.OutboxTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DltEvent implements EventProcessor{
    private final ProducerEvent producerEvent;
    private final KafkaTopicConfig kafkaTopicConfig;

    @Override
    public void execute(OutboxTable outboxTable) {
        producerEvent.sendUploadSubscriptionDlt(
                kafkaTopicConfig.getFileUpdateDltSubscription(),
                outboxTable.getEventId(),
                outboxTable.getPayload());
    }
}
