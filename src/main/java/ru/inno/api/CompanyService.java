package ru.inno.api;

import ru.inno.model.Company;
import ru.inno.model.CreateCompanyResponse;
import ru.inno.model.UserInfo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public interface CompanyService extends Authorizable {

    List<Company> getAll() throws IOException;

    List<Company> getAll(boolean isActive) throws IOException;

    Company getById(int id) throws IOException;

    ApiResponse<CreateCompanyResponse> create(String name) throws IOException;

    ApiResponse<CreateCompanyResponse> create(String name, String description) throws IOException;

    void deleteById(int id);

    Company edit(int id, String newName);

    Company edit(int id, String newName, String newDescription);

    Company changeStatus(int id, boolean isActive);

}
