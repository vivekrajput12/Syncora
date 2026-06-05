package com.collaboration.syncora.controllers;

import com.collaboration.syncora.dtos.request.ChatMessageReq;
import com.collaboration.syncora.dtos.response.ChatMsgResponse;
import com.collaboration.syncora.models.converstations.ChatMessages;
import com.collaboration.syncora.services.conversations.Chat;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ChatController {
    private SimpMessagingTemplate simpMessagingTemplate;
    private Chat chatService;
    @MessageMapping("/chat/private/{receiverUid}")
    public void sendPrivateMessage(@DestinationVariable String receiverUid , ChatMessageReq chatMessageReq , Principal principal){
        String senderUid = principal.getName();
        chatMessageReq.setSenderUid(senderUid);
        chatMessageReq.setReceiverUid(receiverUid);
        ChatMessages message = chatService.saveChatMsg(chatMessageReq);
        ChatMsgResponse response = toResponse(message);
        simpMessagingTemplate.convertAndSendToUser(receiverUid ,"/queue/messages" ,response );
    }
    private ChatMsgResponse toResponse(ChatMessages msg){
        return new ChatMsgResponse(
                msg.getMessageId(),
                msg.getSenderUid(),
                msg.getReceiverUid(),
                msg.getGroupId(),
                msg.getContent(),
                msg.getCreatedAt(),
                msg.isDelivered(),
                msg.isRead()
        );
    }
}

/*
grp chat
grpId
content
senderId
 */

/*
private chat
senderId
reciverId
content
 */

