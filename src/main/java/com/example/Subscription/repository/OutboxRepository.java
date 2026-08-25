package com.example.Subscription.repository;

import com.example.Subscription.enums.FileRunSubscription;
import com.example.Subscription.enums.StatusEvent;
import com.example.Subscription.model.entity.OutboxTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<OutboxTable, StatusEvent> {
    Page<OutboxTable> findByFileRunSubscription(FileRunSubscription event, Pageable pageable);

}
