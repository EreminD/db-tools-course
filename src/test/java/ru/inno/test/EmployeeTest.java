package ru.inno.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.inno.db.CompanyRepository;
import ru.inno.db.EmployeeRepository;
import ru.inno.ext.CompanyRepositoryJpaResolver;
import ru.inno.ext.EmployeeRepositoryJpaResolver;

@ExtendWith({EmployeeRepositoryJpaResolver.class, CompanyRepositoryJpaResolver.class})
public class EmployeeTest {

    @Test
    public void getEmployeeList(EmployeeRepository repository) {
    }

    @Test
    public void getEmployee(EmployeeRepository repository) {
    }

    @Test
    public void companyListTest(CompanyRepository repository) {
    }

}
