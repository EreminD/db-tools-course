package ru.inno.model;


import java.sql.Timestamp;
import java.util.Objects;

public class CompanyEntity {
    private int id;
    private boolean isActive;
    private Object createDateTime;
    private Object lastChangedDateTime;
    private String name;
    private String description;

    private Timestamp deletedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Object getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Object createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Object getLastChangedDateTime() {
        return lastChangedDateTime;
    }

    public void setLastChangedDateTime(Object lastChangedDateTime) {
        this.lastChangedDateTime = lastChangedDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, createDateTime, lastChangedDateTime, name, description, deletedAt);
    }
}

