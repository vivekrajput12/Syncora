package com.collaboration.syncora.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "department")
public class Department extends OrgScopedEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;
    private String departmentName;
    private Instant createdAt;
//    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Board> boards;
}
