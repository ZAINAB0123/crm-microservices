package ru.zainab.authservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.zainab.authservice.exception.error.ErrorResponse;
import ru.zainab.authservice.exception.error.FieldErrorResponse;

import java.time.Instant;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> emailAlreadyExistsException(
            EmailAlreadyExistsException e,
            HttpServletRequest request
    ) {
        log.warn("Email already exists {}", e.getMessage());
        return buildError(
                "EMAIL_ALREADY_EXISTS",
                e.getMessage(),
                List.of(),
                HttpStatus.CONFLICT,
                request);
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<ErrorResponse> invalidRefreshTokenException(
            InvalidRefreshTokenException e,
            HttpServletRequest request
    ) {
        log.warn(" Refresh Token not found {}", e.getMessage());
        return buildError(
                "REFRESH_TOKEN_NOT_FOUND",
                e.getMessage(),
                List.of(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(
            UserNotFoundException e,
            HttpServletRequest request
    ) {
        log.warn(" User not found {}", e.getMessage());
        return buildError(
                "USER_NOT_FOUND",
                e.getMessage(),
                List.of(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    private ResponseEntity<ErrorResponse> buildError(
            String code,
            String message,
            List<FieldErrorResponse> errors,
            HttpStatus status,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                code,
                message,
                request.getRequestURI(),
                Instant.now(),
                errors
        );

        return ResponseEntity.status(status).body(errorResponse);
    }
}

