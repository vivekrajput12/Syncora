package com.collaboration.syncora.models.converstations;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity(name = "chatList")
public class ChatList {
    @Id
    private Long conversationId;
    private Long uid;
    private String lastMessage;
    private LocalDateTime lastMessageAt;
    private Integer unreadCount;
    private Boolean pinned;
    private Boolean muted;
    private Boolean archived;
}