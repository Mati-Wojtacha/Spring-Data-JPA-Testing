package org.dataBaseTest.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "departments")
public class DepartmentsEntity {
    @Id
    @GeneratedValue
    private int idDepartment;
    private String address;
    private String departmentName;
    private String description;
    private String mainMail;
    private String mainPhone;
    private String mainwww;

    @ManyToMany(mappedBy = "departments")
    private Set<UsersEntity> users;

    public DepartmentsEntity(int idDepartment, String address, String departmentName, String description, String mainmail, String mainphone, String mainwww, Set<UsersEntity> users) {
        this.idDepartment = idDepartment;
        this.address = address;
        this.departmentName = departmentName;
        this.description = description;
        this.mainMail = mainmail;
        this.mainPhone = mainphone;
        this.mainwww = mainwww;
        this.users = users;
    }

    public DepartmentsEntity() {
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int iddepartment) {
        this.idDepartment = iddepartment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentname) {
        this.departmentName = departmentname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainMail() {
        return mainMail;
    }

    public void setMainMail(String mainmail) {
        this.mainMail = mainmail;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainphone) {
        this.mainPhone = mainphone;
    }

    public String getMainwww() {
        return mainwww;
    }

    public void setMainwww(String mainwww) {
        this.mainwww = mainwww;
    }

    public Set<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UsersEntity> users) {
        this.users = users;
    }
}
