package com.collaboration.syncora.repository;

import com.collaboration.syncora.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findUserByUserName(String username);
    Optional<User> findUserByUid(String uid);
    Optional<User> findUserByEmail(String email);
}
