package ru.inno.ext;

import org.junit.jupiter.api.extension.*;
import ru.inno.db.CompanyRepository;
import ru.inno.db.CompanyRepositoryJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CompanyRepositoryResolver implements ParameterResolver{
    Connection connection = null;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CompanyRepository.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        System.out.println("connecting");
        // TODO: define in .properties
        String connectionString = "jdbc:postgresql://dpg-chdkl0ak728nnn00sqv0-a.frankfurt-postgres.render.com/x_clients_db_yjdt";
        String user = "x_clients_user";
        String pass = "2hdwfMCel2i7SyZeOghoUVVOOwnpyfEL";
        try {
            connection = DriverManager.getConnection(connectionString, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new CompanyRepositoryJdbc(connection);
    }

}
