package ru.inno.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.inno.api.CompanyService;
import ru.inno.db.CompanyRepository;
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
        CompanyEntity dbEntity = repository.create(faker.company().name());
        Company apiEntity = service.getById(dbEntity.getId());
        assertEquals(dbEntity.getId(), apiEntity.getId());
        assertEquals(dbEntity.getName(), apiEntity.getName());
        assertTrue(apiEntity.isActive());
        assertEquals(dbEntity.getDescription(), apiEntity.getDescription());
    }

    @Test
    @DisplayName("Этот проверяет, что можно создавать компании")
    public void shouldCreateCompany(CompanyService service, CompanyRepository repository) throws IOException, SQLException {
        Faker faker = new Faker(new Locale("ru"));
        String nameToBe = faker.company().name();
        String descriptionToBe = faker.address().fullAddress();
        CreateCompanyResponse response = service.create(nameToBe, descriptionToBe);
        int newId = response.getId();

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
    public void shouldNotCreateCompany401(CompanyService service) throws IOException {

    }
}
