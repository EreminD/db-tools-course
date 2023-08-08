package ru.inno.ext;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.inno.db.EmployeeRepository;
import ru.inno.db.EmployeeRepositoryJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import java.util.Properties;

public class EmployeeRepositoryJpaResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(EmployeeRepository.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        Properties props = new Properties();
        props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.put("hibernate.connection.url", "jdbc:postgresql://dpg-chdkl0ak728nnn00sqv0-a.frankfurt-postgres.render.com/x_clients_db_yjdt");
        props.put("hibernate.connection.username", "x_clients_user");
        props.put("hibernate.connection.password", "2hdwfMCel2i7SyZeOghoUVVOOwnpyfEL");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.connection.autocommit", "true");
        props.put("hibernate.hbm2ddl.auto", "validate");

        Object em = extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("em");
        if (em == null) {
            PersistenceUnitInfo persistenceUnitInfo = new MyPersistenceUnitInfo(props);
            HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
            EntityManagerFactory factory = hibernatePersistenceProvider.createContainerEntityManagerFactory(persistenceUnitInfo, props);
            em = factory.createEntityManager();
            extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("em", em);
        }
        return new EmployeeRepositoryJpa((EntityManager) em);
    }
}
