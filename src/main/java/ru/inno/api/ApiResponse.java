package ru.inno.api;

import okhttp3.Headers;

import java.util.Objects;

public class ApiResponse<T> {
    private final Headers headers;
    private final int statusCode;
    private final T body;

    public ApiResponse(Headers headers, int statusCode, T body) {
        this.headers = headers;
        this.statusCode = statusCode;
        this.body = body;
    }

    public Headers getHeaders() {
        return headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiResponse<?> that)) return false;
        return getStatusCode() == that.getStatusCode() && Objects.equals(getHeaders(), that.getHeaders()) && Objects.equals(getBody(), that.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeaders(), getStatusCode(), getBody());
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "headers=" + headers +
                ", statusCode=" + statusCode +
                ", body=" + body +
                '}';
    }
}
