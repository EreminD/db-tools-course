package ru.inno.db;

import ru.inno.model.db.CompanyEntity;

import java.sql.SQLException;
import java.util.List;

public interface CompanyRepository {

    List<CompanyEntity> getAll() throws SQLException;

    List<CompanyEntity> getAll(boolean isActive);

    CompanyEntity getLast() throws SQLException;

    CompanyEntity getById(int id);

    int create(String name) throws SQLException;

    int create(String name, String description) throws SQLException;

    void deleteById(int id);
}
