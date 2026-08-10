package com.example.Subscription.service;

import com.example.Subscription.model.entity.OutboxTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class PolicyEvent {
    @Value("${outbox.check-lock.attempts}")
    private Integer attempts;
    @Value("${outbox.check-lock.timeout}")
    private Duration timeout;

    private boolean checkTimeLimit(OutboxTable outboxTable) {
        Instant timeToLive = outboxTable.getTimeToLive();
        Duration between = Duration.between(timeToLive, Instant.now());
        return between.compareTo(timeout) >= 0;
    }

    private boolean isAttemptsLimitReached(OutboxTable outboxTable) {
        return outboxTable.getAttempts() >= attempts;
    }

    public void startCounterPolicy(OutboxTable outboxTable) {
        if (outboxTable.getTimeToLive() == null) {
            outboxTable.setTimeToLive(Instant.now());
        }
        outboxTable.setAttempts(outboxTable.getAttempts() + 1);
    }

    public boolean checkPolicyLimitRetry(OutboxTable outboxTable) {
        return checkTimeLimit(outboxTable) && isAttemptsLimitReached(outboxTable);
    }
}
