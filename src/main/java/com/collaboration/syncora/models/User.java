package com.collaboration.syncora.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @Column(length = 36, updatable = false, nullable = false)
    private String publicId;
    @Column(length = 100)
    private String name;

    @Column(unique = true , length = 30)
    @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
    private String userName;
    private String mobile;

    @Column(unique = true , length = 70)
    private String email;
    private String password;
    @Column(length = 150)
    private String address;
    private LocalDate dob;
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name="roleId" , referencedColumnName = "roleId")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orgId" , referencedColumnName = "orgId")
    private Organization organization;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_team",
            joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "teamId")
    )
    private List<Department> teams;


    @PrePersist
    private void generateRandomPublicid(){
        if(publicId == null){
            publicId = UUID.randomUUID().toString();
        }
    }
}
