package ru.inno.ext;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.inno.api.CompanyService;
import ru.inno.api.CompanyServiceImpl;
import ru.inno.api.LogInterceptor;

public class CompanyServiceResolver implements ParameterResolver {
    private final static String KEY = "CompanyService";

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CompanyService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        CompanyService service = (CompanyService) extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).get(KEY);
        if (service == null) {
            Interceptor interceptor = new LogInterceptor();
            OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor).build();
            service = new CompanyServiceImpl(client, "https://x-clients-be.onrender.com");
            extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).put(KEY, service);
        }
        return service;
    }
}
