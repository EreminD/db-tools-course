package ru.inno.ext;

import org.junit.jupiter.api.extension.*;
import ru.inno.db.CompanyRepository;
import ru.inno.db.CompanyRepositoryJdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class CompanyRepositoryJdbcResolver implements ParameterResolver, BeforeAllCallback, AfterAllCallback {
    private Connection connection = null;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CompanyRepository.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return new CompanyRepositoryJdbc(connection);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        System.out.println("disconnecting");
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        System.out.println("connecting");
        // TODO: define in .properties
        String connectionString = "jdbc:postgresql://dpg-chdkl0ak728nnn00sqv0-a.frankfurt-postgres.render.com/x_clients_db_yjdt";
        String user = "x_clients_user";
        String pass = "2hdwfMCel2i7SyZeOghoUVVOOwnpyfEL";
        connection = DriverManager.getConnection(connectionString, user, pass);
    }
}
