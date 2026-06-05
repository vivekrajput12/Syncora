package com.collaboration.syncora.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "departments")
public class Department extends OrgScopedEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentName;
    private Instant createdAt;
//    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Board> boards;
}
