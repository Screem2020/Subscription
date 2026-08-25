package com.example.Subscription.service;

import com.example.Subscription.enums.FileRunSubscription;
import com.example.Subscription.model.entity.OutboxTable;
import com.example.Subscription.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OutboxManager {

    private final OutboxRepository outboxRepository;

    public void save(OutboxTable outboxTable) {
        outboxRepository.save(outboxTable);
    }

    public List<OutboxTable> checkTimeSubscription() {
        Pageable pageable = PageRequest.of(0, 100);
        return outboxRepository.findByFileRunSubscription(FileRunSubscription.RUN, pageable)
                .stream()
                .toList();
    }
}
