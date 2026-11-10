package ru.zainab.authservice.account.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.zainab.authservice.account.enums.AccountRole;
import ru.zainab.authservice.account.enums.AccountStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "auth_accounts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    @Column(nullable = false, unique = true, length = 255)
    private String email;
    @Column(nullable = false, length = 255)
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountRole role;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    @Column(nullable = false)
    private Instant updatedAt;
    @Version
    private Long version;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }
    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
    public void deactivate(){
       this.status = AccountStatus.INACTIVE;
    }
    public void activate(){
        this.status = AccountStatus.ACTIVE;
    }
    public AuthAccount(String email,String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = AccountRole.USER;
        this.status = AccountStatus.ACTIVE;


    }
}
