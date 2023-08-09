package ru.inno.ext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import ru.inno.db.CompanyRepository;
import ru.inno.db.CompanyRepositoryJpa;
import ru.inno.ext.props.PropertyProvider;

import java.util.Properties;

public class CompanyRepositoryJpaResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CompanyRepository.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        Properties props = PropertyProvider.getInstance().getProps();

        Object em = extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("em");
        if (em == null) {
            PersistenceUnitInfo persistenceUnitInfo = new MyPersistenceUnitInfo(props);
            HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
            EntityManagerFactory factory = hibernatePersistenceProvider.createContainerEntityManagerFactory(persistenceUnitInfo, props);
            em = factory.createEntityManager();
            extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("em", em);
        }
        return new CompanyRepositoryJpa((EntityManager) em);
    }
}
