package ru.inno.db;

import ru.inno.model.CompanyEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepositoryJdbc implements CompanyRepository {
    private final static String SELECT_ALL = "select * from company where \"deletedAt\" is null";
    private final static String SELECT_LAST = "select * from company where \"deletedAt\" is null ORDER BY id DESC LIMIT 1";
    private final static String INSERT = "insert into company(name) values(?)";
    private final static String INSERT_WITH_DESC = "insert into company(name, description) values(?, ?)";

    private Connection connection;

    public CompanyRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<CompanyEntity> getAll() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT_ALL);
        List<CompanyEntity> companies = new ArrayList<>();
        while (resultSet.next()) {
            CompanyEntity entity = new CompanyEntity();
            entity.setName(resultSet.getString("name"));
            entity.setActive(resultSet.getBoolean("isActive"));
            entity.setId(resultSet.getInt("id"));
            entity.setDescription(resultSet.getString("description"));
            entity.setDeletedAt(resultSet.getTimestamp("deletedAt"));
            entity.setDeletedAt(resultSet.getTimestamp("createDateTime"));
            entity.setDeletedAt(resultSet.getTimestamp("lastChangedDateTime"));
            companies.add(entity);
        }
        return companies;
    }

    @Override
    public List<CompanyEntity> getAll(boolean isActive) {
        return null;
    }

    @Override
    public CompanyEntity getLast() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT_LAST);
        resultSet.next();
        CompanyEntity entity = new CompanyEntity();
        entity.setName(resultSet.getString("name"));
        entity.setActive(resultSet.getBoolean("isActive"));
        entity.setId(resultSet.getInt("id"));
        entity.setDescription(resultSet.getString("description"));
        entity.setDeletedAt(resultSet.getTimestamp("deletedAt"));
        entity.setCreateDateTime(resultSet.getTimestamp("createDateTime"));
        entity.setLastChangedDateTime(resultSet.getTimestamp("lastChangedDateTime"));
        return entity;
    }

    @Override
    public CompanyEntity getById(int id) {
        // TODO: implement
        return null;
    }

    @Override
    public CompanyEntity create(String name) throws SQLException {
        // TODO: get inserted ID;
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        return getLast();
    }

    @Override
    public CompanyEntity create(String name, String description) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
