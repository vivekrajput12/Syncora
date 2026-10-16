package com.collaboration.syncora.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "task")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Task extends OrgScopedEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @Column(nullable = false)
    private String taskName;
    private String description;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private Long columnId;
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "column_id")
//    private BoardColumn column;
}
