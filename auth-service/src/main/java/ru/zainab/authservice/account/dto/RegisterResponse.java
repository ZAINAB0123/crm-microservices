package ru.zainab.authservice.account.dto;

import ru.zainab.authservice.account.enums.AccountRole;
import ru.zainab.authservice.account.enums.AccountStatus;

import java.time.Instant;
import java.util.UUID;

public record RegisterResponse(
        UUID id,
        String email,
        AccountRole role,
        AccountStatus status,
        Instant createdAt,
        Instant updatedAt,
        Long version
) {
}
