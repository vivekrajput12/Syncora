package com.collaboration.syncora.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tasks")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Task extends OrgScopedEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId" , referencedColumnName = "boardId")
    private Board board;
}
