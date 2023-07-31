package ru.inno.api;

import java.io.IOException;

public interface Authorizable<T> {

    T auth(String username, String password) throws IOException;
}
