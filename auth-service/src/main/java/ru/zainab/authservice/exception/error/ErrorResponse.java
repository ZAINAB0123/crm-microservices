package ru.zainab.authservice.exception.error;


import java.time.Instant;
import java.util.List;

public record ErrorResponse (
        String code,
        String message,
        String path,
        Instant timestamp,
        List<FieldErrorResponse> errors
) {
}
