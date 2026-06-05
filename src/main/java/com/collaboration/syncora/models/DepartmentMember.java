package com.collaboration.syncora.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "departmentMembers")
public class DepartmentMember extends OrgScopedEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentName;
    private Long teamId;
    private String uid;
    private Instant joinedAt;
}
