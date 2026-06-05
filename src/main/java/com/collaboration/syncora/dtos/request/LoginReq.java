package com.collaboration.syncora.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginReq {
    @NotBlank(message = "Invalid Email")
    private String email;
    @NotBlank(message = "Invalid Password")
    private String password;
}
