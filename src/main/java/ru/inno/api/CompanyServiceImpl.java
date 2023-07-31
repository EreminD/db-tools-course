package ru.inno.api;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import ru.inno.model.Company;
import ru.inno.model.CreateCompanyRequest;
import ru.inno.model.CreateCompanyResponse;
import ru.inno.model.UserInfo;

import java.io.IOException;
import java.util.List;

public class CompanyServiceImpl implements CompanyService {
    public static final MediaType APPLICATION_JSON = MediaType.parse("application/json; charset=UTF-8");
    private static final String PATH = "company";
    private final String BASE_PATH;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public CompanyServiceImpl(OkHttpClient client, String url) {
        this.client = client;
        this.BASE_PATH = url;
        this.mapper = new ObjectMapper();
    }

    @Override
    public List<Company> getAll() throws IOException {
        HttpUrl url = getUrl().build();
        return getCompanies(url);
    }

    @Override
    public List<Company> getAll(boolean isActive) throws IOException {
        HttpUrl url = getUrl()
                .addQueryParameter("active", Boolean.toString(isActive))
                .build();
        return getCompanies(url);
    }

    @Override
    public Company getById(int id) throws IOException {
        HttpUrl url = getUrl()
                .addPathSegment(Integer.toString(id))
                .build();
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), Company.class);
    }

    @Override
    public CreateCompanyResponse create(String name) throws IOException {
        return null;
    }

    @Override
    public CreateCompanyResponse create(String name, String description) throws IOException {
        HttpUrl url = getUrl().build();
        CreateCompanyRequest body1 = new CreateCompanyRequest(name, description);
        RequestBody jsonBody1 = RequestBody.create(mapper.writeValueAsString(body1), APPLICATION_JSON);
        Request.Builder request1 = new Request.Builder().post(jsonBody1).url(url);

        // TODO: make it optional
        String token = getToken("roxy", "ert");
        request1.addHeader("x-client-token", token);


        Response response1 = this.client.newCall(request1.build()).execute();
        return mapper.readValue(response1.body().string(), CreateCompanyResponse.class);
    }

    private String getToken(String user, String pass) throws IOException {
        String body = "{\"username\": \"" + user + "\", \"password\": \"" + pass + "\"}";
        RequestBody jsonBody = RequestBody.create(body, APPLICATION_JSON);
        Request request = new Request.Builder().post(jsonBody).url(BASE_PATH + "/auth/login").build();
        Response response = this.client.newCall(request).execute();
        return mapper.readValue(response.body().string(), UserInfo.class).getUserToken();
    }

    @Override
    public void deleteById(int id) {
    }

    @Override
    public Company edit(int id, String newName) {
        return null;
    }

    @Override
    public Company edit(int id, String newName, String newDescription) {
        return null;
    }

    @Override
    public Company changeStatus(int id, boolean isActive) {
        return null;
    }

    @NotNull
    private HttpUrl.Builder getUrl() {
        return HttpUrl.parse(BASE_PATH).newBuilder().addPathSegment(PATH);
    }

    private List<Company> getCompanies(HttpUrl url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        return mapper.readValue(response.body().string(), new TypeReference<>() {
        });
    }

}