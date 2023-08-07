package ru.inno.db;

import ru.inno.model.db.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository {
    List<EmployeeEntity> getAll();

    EmployeeEntity getById(int id);
}
