package ru.inno.db;

import ru.inno.model.CompanyEntity;

import java.sql.SQLException;
import java.util.List;

public interface CompanyRepository {

    List<CompanyEntity> getAll() throws SQLException;

    List<CompanyEntity> getAll(boolean isActive);

    CompanyEntity getLast() throws SQLException;
    CompanyEntity getById(int id);

    CompanyEntity create(String name) throws SQLException;

    CompanyEntity create(String name, String description);

    void deleteById(int id);
}
