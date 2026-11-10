package ru.zainab.authservice.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zainab.authservice.account.entity.AuthAccount;

import java.util.Optional;
import java.util.UUID;

public interface AuthAccountRepository extends JpaRepository<AuthAccount, UUID> {
    Optional<AuthAccount> findByEmail(String email);
    boolean existsByEmail(String email);
}
