package org.dataBaseTest;

import org.dataBaseTest.model.DepartmentsEntity;
import org.dataBaseTest.model.UsersEntity;
import org.dataBaseTest.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testCreateUpdateDeleteDepartment() {
        // CREATE
        DepartmentsEntity department = new DepartmentsEntity();
        department.setDepartmentName("Example Department");
        department.setDescription("Example description");
        department.setAddress("123 Main St.");
        department.setMainMail("example@example.com");
        department.setMainPhone("123-456-7890");
        department.setMainwww("www.example.com");

        department = departmentRepository.save(department);
        assertNotNull(department.getIdDepartment());

        // SELECT
        Optional<DepartmentsEntity> selectedDepartmentOpt = departmentRepository.findById(department.getIdDepartment());
        assertTrue(selectedDepartmentOpt.isPresent());
        DepartmentsEntity selectedDepartment = selectedDepartmentOpt.get();
        assertEquals("Example Department", selectedDepartment.getDepartmentName());
        assertEquals("Example description", selectedDepartment.getDescription());
        assertEquals("123 Main St.", selectedDepartment.getAddress());
        assertEquals("example@example.com", selectedDepartment.getMainMail());
        assertEquals("123-456-7890", selectedDepartment.getMainPhone());
        assertEquals("www.example.com", selectedDepartment.getMainwww());

        // UPDATE
        String newAddress = "456 Elm St.";
        selectedDepartment.setAddress(newAddress);
        departmentRepository.save(selectedDepartment);

        DepartmentsEntity updatedDepartment = departmentRepository.findById(selectedDepartment.getIdDepartment()).orElse(null);
        assertNotNull(updatedDepartment);
        assertEquals(newAddress, updatedDepartment.getAddress());

        // DELETE
        departmentRepository.delete(updatedDepartment);
        assertFalse(departmentRepository.existsById(updatedDepartment.getIdDepartment()));
    }

    @Test
    public void testDepartmentUsersRelation() {
        // CREATE
        DepartmentsEntity department = new DepartmentsEntity();
        department.setDepartmentName("Example Department");
        department.setDescription("Example description");
        department.setAddress("123 Main St.");
        department.setMainMail("example@example.com");
        department.setMainPhone("123-456-7890");
        department.setMainwww("www.example.com");

        UsersEntity user1 = new UsersEntity();
        user1.setFirstname("John");
        user1.setLastname("Doe");

        UsersEntity user2 = new UsersEntity();
        user2.setFirstname("Jane");
        user2.setLastname("Smith");


        Set<UsersEntity> users = new HashSet<>();
        users.add(user1);
        users.add(user2);

        department.setUsers(users);

        department = departmentRepository.save(department);
        assertNotNull(department.getIdDepartment());

        // SELECT
        Optional<DepartmentsEntity> selectedDepartmentOpt = departmentRepository.findById(department.getIdDepartment());
        assertTrue(selectedDepartmentOpt.isPresent());
        DepartmentsEntity selectedDepartment = selectedDepartmentOpt.get();
        assertEquals(2, selectedDepartment.getUsers().size());
        assertTrue(selectedDepartment.getUsers().contains(user1));
        assertTrue(selectedDepartment.getUsers().contains(user2));

        // DELETE
        departmentRepository.delete(selectedDepartment);
        assertFalse(departmentRepository.existsById(selectedDepartment.getIdDepartment()));
    }
}
