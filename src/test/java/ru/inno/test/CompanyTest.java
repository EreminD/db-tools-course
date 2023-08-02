package ru.inno.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.inno.api.ApiResponse;
import ru.inno.api.AuthorizeService;
import ru.inno.api.CompanyService;
import ru.inno.db.CompanyRepository;
import ru.inno.ext.Authorized;
import ru.inno.ext.CompanyRepositoryResolver;
import ru.inno.ext.CompanyServiceResolver;
import ru.inno.model.ApiError;
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

    @Test
    public void test0(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test1(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test2(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test3(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test4(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test5(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test6(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test7(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test8(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test9(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test10(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test11(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test12(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test13(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test14(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test15(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test16(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test17(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test18(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test19(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test20(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test21(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test22(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test23(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test24(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test25(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test26(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test27(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test28(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test29(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test30(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test31(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test32(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test33(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test34(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test35(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test36(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test37(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test38(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test39(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test40(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test41(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test42(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test43(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test44(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test45(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test46(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test47(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test48(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test49(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test50(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test51(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test52(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test53(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test54(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test55(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test56(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test57(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test58(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test59(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test60(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test61(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test62(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test63(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test64(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test65(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test66(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test67(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test68(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test69(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test70(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test71(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test72(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test73(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test74(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test75(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test76(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test77(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test78(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test79(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test80(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test81(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test82(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test83(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test84(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test85(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test86(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test87(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test88(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test89(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test90(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test91(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test92(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test93(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test94(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test95(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test96(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test97(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test98(CompanyService service, CompanyRepository repository) {
    }

    @Test
    public void test99(CompanyService service, CompanyRepository repository) {
    }

}
