package ru.inno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompanyDBHelper implements AutoCloseable {

    private final Connection connection;

    public CompanyDBHelper(String url, String usr, String pass) throws SQLException {
        connection = DriverManager.getConnection(url, usr, pass);
    }

    public int add(String name, String description) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into company(name, description) values(?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, description);
        return preparedStatement.executeUpdate();
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
