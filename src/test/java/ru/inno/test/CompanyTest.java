package ru.inno.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.inno.api.ApiResponse;
import ru.inno.api.AuthorizeService;
import ru.inno.api.CompanyService;
import ru.inno.db.CompanyRepository;
import ru.inno.ext.Authorized;
import ru.inno.ext.CompanyRepositoryResolver;
import ru.inno.ext.CompanyServiceResolver;
import ru.inno.model.Company;
import ru.inno.model.CompanyEntity;
import ru.inno.model.CreateCompanyResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({CompanyServiceResolver.class, CompanyRepositoryResolver.class})
public class CompanyTest {

    @Test
    @DisplayName("Этот проверяет, что мне приходит список компаний")
    public void companyListTest(CompanyService service, CompanyRepository repository) throws IOException, SQLException {
        int companyCount = repository.getAll().size();
        List<Company> list = service.getAll();
        assertEquals(companyCount, list.size());
    }

    @Test
    @DisplayName("Этот проверяет, что работает фильтр active")
    public void companyListDisabledTest(CompanyService service) throws IOException {
        // TODO: select from DB
        List<Company> list = service.getAll(false);
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Этот проверяет, что можно запросить компанию по id")
    public void companyGetByIdTest(CompanyService service, CompanyRepository repository) throws IOException, SQLException {
        Faker faker = new Faker(new Locale("ru"));
        String name = faker.company().name();
        int entityId = repository.create(name);
        Company apiEntity = service.getById(entityId);
        assertEquals(entityId, apiEntity.getId());
        assertEquals(name, apiEntity.getName());
        assertTrue(apiEntity.isActive());
        assertNull(null, apiEntity.getDescription());
    }

    @Test
    @DisplayName("Этот проверяет, что можно создавать компании")
    public void shouldCreateCompany(
            @Authorized(username = "roxy", password = "animal-fairy") CompanyService service,
            CompanyService serviceNoAuth,
            CompanyRepository repository,
            AuthorizeService authorizeService
    ) throws IOException, SQLException {
        Faker faker = new Faker(new Locale("ru"));
        String nameToBe = faker.company().name();
        String descriptionToBe = faker.address().fullAddress();
        ApiResponse<CreateCompanyResponse> response = service.create(nameToBe, descriptionToBe);
        int newId = response.getBody().getId();

//        CompanyEntity entity = repository.getById(newId); // Right way
        CompanyEntity entity = repository.getLast();
        assertEquals(newId, entity.getId());
        assertEquals(nameToBe, entity.getName());
        assertEquals(descriptionToBe, entity.getDescription());
        assertTrue(entity.isActive());
        assertNull(entity.getDeletedAt());
    }

    @Test
    @DisplayName("Этот проверяет, что приходит 401, если не подложил токен в запрос")
    public void shouldNotCreateCompany401(CompanyService service, CompanyRepository repository) throws IOException, SQLException {
        List<CompanyEntity> before = repository.getAll();

        Faker faker = new Faker(new Locale("ru"));
        String nameToBe = faker.company().name();
        String descriptionToBe = faker.address().fullAddress();

        ApiResponse<CreateCompanyResponse> response = service.create(nameToBe, descriptionToBe);

        List<CompanyEntity> after = repository.getAll();
        assertEquals(before.size(), after.size());
        assertEquals(response.getStatusCode(), 401);
        assertEquals("Unauthorized", response.getApiError().getMessage());
    }
}
