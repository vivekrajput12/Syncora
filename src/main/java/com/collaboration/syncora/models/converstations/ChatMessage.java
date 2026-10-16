package com.collaboration.syncora.models.converstations;

import com.collaboration.syncora.Constants.MessageType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "chatMessage")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private Long senderUid;
    private Long receiverUid;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    private String groupId;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private Boolean delivered;
    private Boolean isRead;
    private LocalDateTime readAt;
    @Enumerated(EnumType.STRING)
    private MessageType messageType;
}
