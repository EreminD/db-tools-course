package ru.inno.model.api;

import java.util.Objects;

public class ApiError {
    private  int statusCode;
    private  String message;

    public ApiError() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
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
