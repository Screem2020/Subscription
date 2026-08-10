package com.example.Subscription.model.entity;

import com.example.Subscription.enums.FileRunSubscription;
import com.example.Subscription.enums.StatusEvent;
import com.example.Subscription.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "outbox_table")
public class OutboxTable {
    @Id
    private UUID eventId;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String payload;
    private Integer attempts;
    @Enumerated(EnumType.STRING)
    private SubscriptionType typeRun;
    @Enumerated(EnumType.STRING)
    private FileRunSubscription fileRunSubscription;
    @Enumerated(EnumType.STRING)
    private StatusEvent statusEvent;
    private Instant timeToLive;
}
