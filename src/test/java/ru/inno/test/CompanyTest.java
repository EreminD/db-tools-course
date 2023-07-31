package ru.inno.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.inno.api.CompanyService;
import ru.inno.ext.HttpClientResolver;
import ru.inno.model.Company;
import ru.inno.model.CreateCompanyResponse;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(HttpClientResolver.class)
public class CompanyTest {

    @Test
    public void companyListTest(CompanyService service) throws IOException {
        List<Company> list = service.getAll();
        assertEquals(18, list.size());
    }

    @Test
    public void companyListDisabledTest(CompanyService service) throws IOException {
        List<Company> list = service.getAll(false);
        assertEquals(2, list.size());
    }

    @Test
    public void companyGetByIdTest(CompanyService service) throws IOException {
        Company company = service.getById(40);
        assertEquals(40, company.getId());
        assertEquals("Барбершоп 'ЦирюльникЪ'", company.getName());
        assertTrue(company.isActive());
    }

    @Test
    public void createCompanyTest(CompanyService service) throws IOException {
        String name = "My new name";
        String username = "roxy";
        String pass = "animal-fairy";
        CreateCompanyResponse response = service.auth(username, pass).create(name);

        Company company = service.getById(response.getId());
        assertEquals(name, company.getName());
        assertNull(company.getDescription());
        assertTrue(company.isActive());
    }
}
