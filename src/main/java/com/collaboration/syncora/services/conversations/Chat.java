package com.collaboration.syncora.services.conversations;

import com.collaboration.syncora.dtos.request.ChatMessageReq;
import com.collaboration.syncora.models.converstations.ChatMessage;
import com.collaboration.syncora.repository.ChatRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class Chat {
    // private ChatRepo chatRepo;
    // public ChatMessage saveChatMsg(ChatMessageReq chatMessageReq){
    //     ChatMessage chatMessages = new ChatMessages();
    //     chatMessages.setSenderUid(chatMessageReq.getSenderUid());
    //     chatMessages.setReceiverUid(chatMessageReq.getReceiverUid());
    //     chatMessages.setContent(chatMessageReq.getContent());
    //     chatMessages.setCreatedAt(LocalDateTime.now());
    //     chatMessages.setDelivered(false);
    //     chatMessages.setRead(false);
    //     chatMessages.setMessageType(chatMessageReq.getType());
    //     ChatMessage message = chatRepo.save(chatMessages);
    //     return message;
    // }

}
