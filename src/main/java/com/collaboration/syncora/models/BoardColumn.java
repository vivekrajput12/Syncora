package com.collaboration.syncora.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "boardColumn")
public class BoardColumn extends OrgScopedEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long columnId;
//    @JoinColumn(name = "boardId")
    private Long boardId;
    private String boardName;
    private String position;
    private Instant createdAt;
}
