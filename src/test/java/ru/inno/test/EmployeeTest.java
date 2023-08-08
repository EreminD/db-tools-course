package ru.inno.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.inno.db.CompanyRepository;
import ru.inno.db.EmployeeRepository;
import ru.inno.ext.CompanyRepositoryJpaResolver;
import ru.inno.ext.EmployeeRepositoryJpaResolver;
import ru.inno.model.db.CompanyEntity;
import ru.inno.model.db.EmployeeEntity;

import java.util.List;

@ExtendWith({EmployeeRepositoryJpaResolver.class, CompanyRepositoryJpaResolver.class})
public class EmployeeTest {

    @Test
    public void getEmployeeList(EmployeeRepository repository) {
        List<EmployeeEntity> all = repository.getAll();
        System.out.println(all.size());
    }

    @Test
    public void getEmployee(EmployeeRepository repository) {
        EmployeeEntity emp = repository.getById(127);
        System.out.println(emp.getFirstName());
        System.out.println(emp.getLastName());
        System.out.println(emp.getPhone());
        System.out.println(emp.getCompany().getId());
        System.out.println(emp.getCompany().getName());
    }

    @Test
    public void companyListTest(CompanyRepository repository) {
        CompanyEntity company40 = repository.getById(40);
        System.out.println(company40.getEmployees().size());
    }
}
