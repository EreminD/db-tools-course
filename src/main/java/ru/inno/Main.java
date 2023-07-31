package ru.inno;

import ru.inno.model.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        String connectionString = "jdbc:postgresql://host/dbname";
        String user = "login";
        String pass = "pass";

        try (Connection connection = DriverManager.getConnection(connectionString, user, pass)) {
            getList(connection);
            //        insert(connection);
            //        sqlInjection(connection);
            //        preparedSelect(connection);
            //        preparedSelect2(connection);
        } catch (SQLException ex) {
            throw ex;
        }


    }

    private static void preparedSelect2(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from company where name = ? ");
        String companyName = new Scanner(System.in).nextLine();

        preparedStatement.setString(1, companyName);

        ResultSet set = preparedStatement.executeQuery();
        if (set.next()) {
            System.out.println(set.getString("description"));
        } else {
            System.err.println("no companies");
        }
    }

    private static void preparedSelect(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from company where id = ? and \"isActive\" = ?");
        int companyId = new Scanner(System.in).nextInt();

        preparedStatement.setInt(1, companyId);
        preparedStatement.setBoolean(2, true);

        ResultSet set = preparedStatement.executeQuery();
        if (set.next()) {
            System.out.println(set.getString("name"));
        } else {
            System.err.println("no companies");
        }
    }

    private static void sqlInjection(Connection connection) throws SQLException {
        String companyId = new Scanner(System.in).nextLine(); // 172; delete from company where id > 100;
        String q = "select * from company where id = " + companyId;

        ResultSet resultSet = connection.createStatement().executeQuery(q);
        if (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        } else {
            System.err.println("no companies");
        }
    }

    private static void insert(Connection connection) throws SQLException {
        String insertQuery = "insert into company(name, description) values('my Java company 1', 'desc'), ('my Java company 2', 'desc')";
//        String deleteQuery = "delete ..";
//        String updateQuery = "update ...";
        int i = connection.createStatement().executeUpdate(insertQuery);
    }

    private static void getList(Connection connection) throws SQLException {
        String select = "select * from company;";
        ResultSet resultSet = connection.createStatement().executeQuery(select);
        List<Company> names = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            boolean isActive = resultSet.getBoolean("isActive");
            int id = resultSet.getInt("id");

//            Company c = new Company(id, name, "", isActive);
//            names.add(c);
        }

        System.out.println(names);
    }
}


