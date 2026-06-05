package com.collaboration.syncora.dtos.request;
import com.collaboration.syncora.Constants.MessageType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
//public class ChatMessageReq {
//    @JsonIgnore
//    private String senderUid;
//    private String content;
//    @Nullable
//    private String groupId;
//    @Nullable
//    private MessageType type;
//    public enum MessageType {
//        CHAT, JOIN, LEAVE
//    }
//}
public class ChatMessageReq {

    @JsonIgnore
    private String senderUid;   // Always set server-side from Principal

    @Nullable
    private String receiverUid; // For private chat (null for group)

    @Nullable
    private String groupId;     // For group chat (null for private)

    private String content;     // Text or URL

    private MessageType type;   // TEXT, IMAGE, VIDEO, FILE, SYSTEM
}