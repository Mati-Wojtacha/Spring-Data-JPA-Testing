package org.dataBaseTest.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UsersEntity {
    @Id
    @GeneratedValue
    private int idUser;
    private String description;
    private String firstname;
    private String lastname;
    private String password;
    private String username;

    @OneToMany(mappedBy = "user")
    private Set<PaymentEntity> payments;

    @ManyToMany
    @JoinTable(
            name = "user_department",
            joinColumns = @JoinColumn(name = "iduser"),
            inverseJoinColumns = @JoinColumn(name = "iddepartment")
    )
    private Set<DepartmentsEntity> departments;

    public UsersEntity(int idUser, String description, String firstname, String lastname, String password, String username, Set<PaymentEntity> payments, Set<DepartmentsEntity> departments) {
        this.idUser = idUser;
        this.description = description;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
        this.payments = payments;
        this.departments = departments;
    }

    public UsersEntity() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(Set<PaymentEntity> payments) {
        this.payments = payments;
    }

    public Set<DepartmentsEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentsEntity> departments) {
        this.departments = departments;
    }
}
