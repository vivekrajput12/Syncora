package com.collaboration.syncora.models.converstations;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity(name = "chatList")
public class ChatList {
    private Long conversationId;
    private String uid;
    private String lastMessage;
    private LocalDateTime lastMessageAt;
    private int unreadCount;
    private boolean pinned;
    private boolean muted;
    private boolean archived;
}