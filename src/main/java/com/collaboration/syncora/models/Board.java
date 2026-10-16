package com.collaboration.syncora.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "boards")
public class Board extends OrgScopedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long boardId;
    private String name;
    @Column(name = "departmentId", nullable = false)
    private Long departmentId;
    private Long createdBy;

    private Instant createdAt;
    private Instant updatedAt;
}