package ru.inno.db;

import ru.inno.model.db.EmployeeEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeRepositoryJpa implements EmployeeRepository {
    private final EntityManager entityManager;

    public EmployeeRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return entityManager
                .createQuery("SELECT e FROM EmployeeEntity e", EmployeeEntity.class)
                .getResultList();
    }

    public EmployeeEntity getById(int id) {
        return entityManager.find(EmployeeEntity.class, id);
    }
}
