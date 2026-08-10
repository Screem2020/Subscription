package com.example.Subscription.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ProducerEvent {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendUploadSubscription(String topic, UUID eventId, String payload) {
        kafkaTemplate.send(topic, eventId.toString(), payload);
    }
    public void sendFiledSubscription(String topic, UUID eventId, String payload) {
        kafkaTemplate.send(topic, eventId.toString(), payload);
    }
    public void sendUploadSubscriptionDlt(String topic, UUID eventId, String payload) {
        kafkaTemplate.send(topic, eventId.toString(), payload);
    }
}
