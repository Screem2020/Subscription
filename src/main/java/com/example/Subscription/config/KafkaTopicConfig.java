package com.example.Subscription.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.kafka.topics")
@Getter
@Setter
public class KafkaTopicConfig {
    private String fileUploadSubscription;
    private String fileFailedSubscription;
    private String fileUpdateDltSubscription;
}
