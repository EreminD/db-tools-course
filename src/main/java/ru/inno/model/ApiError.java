package ru.inno.model;

import java.util.Objects;

public class ApiError {
    private final int statusCode;
    private final String message;

    public ApiError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiError apiError)) return false;
        return getStatusCode() == apiError.getStatusCode() && Objects.equals(getMessage(), apiError.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatusCode(), getMessage());
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
}
