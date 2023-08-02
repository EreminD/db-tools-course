package ru.inno.model.db;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "company", schema = "public", catalog = "x_clients_db_yjdt")
public class CompanyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "\"isActive\"", nullable = false)
    private boolean isActive;
    @Basic
    @Column(name = "\"createDateTime\"", nullable = false)
    private Timestamp createDateTime;
    @Basic
    @Column(name = "\"lastChangedDateTime\"", nullable = false)
    private Timestamp lastChangedDateTime;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 300)
    private String description;
    @Basic
    @Column(name = "\"deletedAt\"", nullable = true)
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

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Object getLastChangedDateTime() {
        return lastChangedDateTime;
    }

    public void setLastChangedDateTime(Timestamp lastChangedDateTime) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyEntity entity)) return false;
        return getId() == entity.getId() && isActive() == entity.isActive() && Objects.equals(getCreateDateTime(), entity.getCreateDateTime()) && Objects.equals(getLastChangedDateTime(), entity.getLastChangedDateTime()) && Objects.equals(getName(), entity.getName()) && Objects.equals(getDescription(), entity.getDescription()) && Objects.equals(getDeletedAt(), entity.getDeletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, createDateTime, lastChangedDateTime, name, description, deletedAt);
    }

    @Override
    public String toString() {
        return "HCompanyEntity{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", createDateTime=" + createDateTime +
                ", lastChangedDateTime=" + lastChangedDateTime +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
