package com.collaboration.syncora.repository;

import com.collaboration.syncora.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role , Long> {
    Optional <Role> findRoleByRoleId(long id);
}
