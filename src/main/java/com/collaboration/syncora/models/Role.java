package com.collaboration.syncora.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    private String roleName;
}