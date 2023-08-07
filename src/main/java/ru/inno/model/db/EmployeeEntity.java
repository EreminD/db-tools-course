package ru.inno.model.db;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "public", catalog = "x_clients_db_yjdt")
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "\"isActive\"", nullable = false)
    private boolean isActive;

    @Column(name = "\"createDateTime\"", nullable = false)
    private Timestamp createDateTime;
    @Column(name = "\"lastChangedDateTime\"", nullable = false)
    private Timestamp lastChangedDateTime;
    @Basic
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    @Basic
    @Column(name = "middle_name", length = 20)
    private String middleName;
    @Basic
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;
    @Basic
    @Column(name = "email", length = 256)
    private String email;
    @Basic
    @Column(name = "avatar_url", length = 1024)
    private String avatarUrl;

    @Basic
    @Column(name = "\"companyId\"", nullable = false)
    private int companyId;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeEntity that)) return false;
        return getId() == that.getId() && isActive() == that.isActive() && getCompanyId() == that.getCompanyId() && Objects.equals(getCreateDateTime(), that.getCreateDateTime()) && Objects.equals(getLastChangedDateTime(), that.getLastChangedDateTime()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getMiddleName(), that.getMiddleName()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getAvatarUrl(), that.getAvatarUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getCreateDateTime(), getLastChangedDateTime(), getFirstName(), getLastName(), getMiddleName(), getPhone(), getEmail(), getAvatarUrl(), getCompanyId());
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", createDateTime=" + createDateTime +
                ", lastChangedDateTime=" + lastChangedDateTime +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
