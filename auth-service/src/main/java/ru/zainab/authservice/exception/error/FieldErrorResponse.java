package ru.zainab.authservice.exception.error;

public record FieldErrorResponse (
        String field,
        String message
){
}
