package com.collaboration.syncora.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtAuthResponse {
    private String token;
}
