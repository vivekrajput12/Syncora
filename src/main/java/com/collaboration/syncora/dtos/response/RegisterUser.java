package com.collaboration.syncora.dtos.response;

import com.collaboration.syncora.models.User;
import lombok.Data;

@Data
public class RegisterUser {
    private String userName;
    private long role;
    public RegisterUser(String userName, long role) {
        this.userName = userName;
        this.role = role;
    }
    public static RegisterUser from(User user) {
        return new RegisterUser(user.getUserName(), user.getRole().getRoleId());
    }
}
