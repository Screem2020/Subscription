package com.example.Subscription.service;

import com.example.Subscription.enums.StatusEvent;
import com.example.Subscription.model.entity.OutboxTable;
import com.example.Subscription.service.processor.DltEvent;
import com.example.Subscription.service.processor.EventProcessor;
import com.example.Subscription.service.processor.FailedEvent;
import com.example.Subscription.service.processor.UploadEvent;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DispatcherEvent {
    private final Map<StatusEvent, EventProcessor> eventProcessors;

    public DispatcherEvent(UploadEvent uploadEvent,
                           FailedEvent failedEvent,
                           DltEvent dltEvent) {
        this.eventProcessors = Map.of(
                StatusEvent.UPLOAD,uploadEvent,
                StatusEvent.FAILED, failedEvent,
                StatusEvent.DLT, dltEvent);
    }

    public void dispatcher(OutboxTable outboxTable) {
        eventProcessors.get(outboxTable.getStatusEvent()).execute(outboxTable);
    }
}
