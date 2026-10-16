package com.collaboration.syncora.dtos.response;

import com.collaboration.syncora.models.Role;
import com.collaboration.syncora.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggedInUserResponse {
    private Long uid;
    private String publicId;
    private String name;
    private String email;
    private Role role;
    private String mobile;
    public static LoggedInUserResponse build(User user){
        return new LoggedInUserResponse(
                user.getUid(),
                user.getPublicId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getMobile()
        );
    }
}
