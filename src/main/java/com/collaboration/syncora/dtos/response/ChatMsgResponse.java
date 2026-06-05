package com.collaboration.syncora.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChatMsgResponse {
    private Long messageId;
    private String senderUid;
    private String receiverUid;
    private String groupId;
    private String content;
    private LocalDateTime createdAt;
    private boolean delivered;
    private boolean read;
}

