package com.collaboration.syncora.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value               // makes class immutable (final fields + no setters)
@Builder             // makes building object very easy
public class AppPrincipal {
    String uid;
    String email;
    List<String> roles;
}