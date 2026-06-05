package com.collaboration.syncora.models.converstations;

import com.collaboration.syncora.Constants.MessageType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "chatMessages")
public class ChatMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageId;
    private String senderUid;
    private String receiverUid;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    private String groupId;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private boolean delivered;
    private boolean isRead;
    private LocalDateTime readAt;
    @Enumerated(EnumType.STRING)
    private MessageType messageType;
}
