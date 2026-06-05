package com.collaboration.syncora.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class OrgScopedEntity {
    @Column(nullable = false , updatable = false)
    private Long orgId;
}
