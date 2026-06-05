package com.collaboration.syncora.dtos.response;

import com.collaboration.syncora.models.Role;
import com.collaboration.syncora.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggedInUserResponse {
    private String uid;
    private String name;
    private String email;
    private Role role;
    private String mobile;
    public static LoggedInUserResponse build(User user){
        return new LoggedInUserResponse(
                user.getUid() , user.getName() , user.getEmail() , user.getRole(), user.getMobile()
        );
//        this.uid = ;
//        this.name = user.getName();
//        this.email = user.getEmail();
//        this.mobile = user.getMobile();
//        this.role = user.getRole();
    }
}
