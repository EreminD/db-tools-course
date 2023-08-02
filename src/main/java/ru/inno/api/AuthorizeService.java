package ru.inno.api;

import ru.inno.model.api.UserInfo;

import java.io.IOException;

public interface AuthorizeService {

    UserInfo auth(String username, String password) throws IOException;
}
