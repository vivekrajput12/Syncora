package com.collaboration.syncora.repository;

import com.collaboration.syncora.models.converstations.ChatMessages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<ChatMessages,Long> {
//    Optional<ChatMessages> save
}
