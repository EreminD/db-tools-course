package ru.inno.ext;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.inno.api.*;
import ru.inno.model.UserInfo;

import java.io.IOException;

public class CompanyServiceResolver implements ParameterResolver {
    private final static String DEFAULT_USER = "roxy";
    private final static String DEFAULT_PASS = "animal-fairy";
    public static final String URL = "https://x-clients-be.onrender.com";

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CompanyService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new LogInterceptor()).build();
        CompanyService service = new CompanyServiceImpl(client, URL);

        if (parameterContext.isAnnotated(Authorized.class)) {
            Authorized auth = parameterContext.getParameter().getAnnotation(Authorized.class);
            AuthorizeService authorizeService = new AuthorizeServiceImpl(client, URL);
            UserInfo userInfo;
            try {
                if (!auth.username().isBlank()) {
                    userInfo = authorizeService.auth(auth.username(), auth.password());
                } else {
                    userInfo = authorizeService.auth(DEFAULT_USER, DEFAULT_PASS);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            service.setToken(userInfo.getUserToken());
        }

        return service;
    }
}
