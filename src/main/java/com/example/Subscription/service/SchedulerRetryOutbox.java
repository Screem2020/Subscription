package com.example.Subscription.service;

import com.example.Subscription.enums.FileRunSubscription;
import com.example.Subscription.enums.StatusEvent;
import com.example.Subscription.model.entity.OutboxTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerRetryOutbox {
    private final OutboxManager outboxManager;
    private final PolicyEvent policyEvent;
    private final DispatcherEvent dispatcherEvent;

    @Transactional
    @Scheduled(fixedRateString = "${scheduler.fixed-rate}")
    @SchedulerLock(
            name = "TaskSchedulerSubscription",
            lockAtLeastFor = "PT1M",
            lockAtMostFor = "PT5M"
    )
    public void SchedulerJob() {
        List<OutboxTable> pageFilterList = outboxManager.checkTimeSubscription();
        if (pageFilterList.isEmpty()) {
            log.info("No overdue subscriptions found");
            return;
        }
        for (OutboxTable outboxTable : pageFilterList) {
            try {
                policyEvent.startCounterPolicy(outboxTable);
                if (policyEvent.checkPolicyLimitRetry(outboxTable)) {
                    log.info("Event:  {}  with status: {} send DLT topic", outboxTable.getEventId(), outboxTable.getStatusEvent());
                    log.info("Policy Limit Exceeded");
                    outboxTable.setStatusEvent(StatusEvent.DLT);
                    outboxTable.setFileRunSubscription(FileRunSubscription.ERROR);
                } else if (outboxTable.getStatusEvent() == StatusEvent.FAILED) {
                    log.info("Event: {} with status: {} send Failed topic", outboxTable.getEventId(), outboxTable.getStatusEvent() );
                    outboxTable.setStatusEvent(StatusEvent.FAILED);
                } else {
                    log.info("Event:  {}  with status: {} send Completed topic", outboxTable.getEventId(), outboxTable.getStatusEvent());
                    outboxTable.setFileRunSubscription(FileRunSubscription.COMPLETED);
                }
                dispatcherEvent.dispatcher(outboxTable);

            } catch (Exception e) {
                outboxTable.setFileRunSubscription(FileRunSubscription.RUN);
                log.error("Error occurred while checking outbox table status {} send retry ", outboxTable.getEventId(), e);
            }
        }
    }
}

