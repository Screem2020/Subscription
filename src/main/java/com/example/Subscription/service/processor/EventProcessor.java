package com.example.Subscription.service.processor;

import com.example.Subscription.model.entity.OutboxTable;

public interface EventProcessor {
    void execute(OutboxTable outboxTable);
}
