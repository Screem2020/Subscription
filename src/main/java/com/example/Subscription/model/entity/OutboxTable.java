package com.example.Subscription.model.entity;

import com.example.Subscription.enums.FileRunSubscription.FileRunSubscription;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;

@Entity
@Data
@Table(name = "outbox_table")
public class OutboxTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String login;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String payload;
    private Integer attempts;
    @Enumerated(EnumType.STRING)
    private FileRunSubscription typeRun;
    @Enumerated(EnumType.STRING)
    private FileRunSubscription fileRunSubscription;
    private Instant timeToLive;
}
