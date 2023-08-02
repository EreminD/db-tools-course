package ru.inno.api;

public interface Authorizable<T> {
    T setToken(String token);
}
