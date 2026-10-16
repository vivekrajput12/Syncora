package com.collaboration.syncora.services;

import com.collaboration.syncora.models.User;
import com.collaboration.syncora.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;
    public User getUserDetailsByUid(Long uid){
        User userDetails = userRepo.findUserByUid(uid).orElseThrow(()->new RuntimeException("Invalid User"));
        return userDetails;
    }
}
