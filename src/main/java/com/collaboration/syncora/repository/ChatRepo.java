package com.collaboration.syncora.repository;

import com.collaboration.syncora.models.converstations.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<ChatMessage,Long> {
//    Optional<ChatMessages> save
}
