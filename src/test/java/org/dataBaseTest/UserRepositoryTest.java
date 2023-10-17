package org.dataBaseTest;

import org.dataBaseTest.model.DepartmentsEntity;
import org.dataBaseTest.model.UsersEntity;
import org.dataBaseTest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUpdateDeleteUser() {
        // CREATE
        UsersEntity user = new UsersEntity();
        user.setFirstname("John");
        user.setLastname("Doe");
        // Ustawienie pozostałych pól...

        user = userRepository.save(user);
        assertNotNull(user.getIdUser());

        // SELECT
        Optional<UsersEntity> selectedUserOpt = userRepository.findById(user.getIdUser());
        assertTrue(selectedUserOpt.isPresent());
        UsersEntity selectedUser = selectedUserOpt.get();
        assertEquals("John", selectedUser.getFirstname());
        assertEquals("Doe", selectedUser.getLastname());
        // Sprawdzenie pozostałych pól...

        // UPDATE
        String newDescription = "Updated description";
        selectedUser.setDescription(newDescription);
        userRepository.save(selectedUser);

        UsersEntity updatedUser = userRepository.findById(selectedUser.getIdUser()).orElse(null);
        assertNotNull(updatedUser);
        assertEquals(newDescription, updatedUser.getDescription());

        // DELETE
        userRepository.delete(updatedUser);
        assertFalse(userRepository.existsById(updatedUser.getIdUser()));
    }

    @Test
    public void testUserDepartmentsRelation() {
        // CREATE
        UsersEntity user = new UsersEntity();
        user.setFirstname("John");
        user.setLastname("Doe");
        // Ustawienie pozostałych pól...

        DepartmentsEntity department1 = new DepartmentsEntity();
        department1.setDepartmentName("Department 1");
        department1.setDescription("Description 1");
        // Ustawienie pozostałych pól...

        DepartmentsEntity department2 = new DepartmentsEntity();
        department2.setDepartmentName("Department 2");
        department2.setDescription("Description 2");
        // Ustawienie pozostałych pól...

        Set<DepartmentsEntity> departments = new HashSet<>();
        departments.add(department1);
        departments.add(department2);

        user.setDepartments(departments);

        user = userRepository.save(user);
        assertNotNull(user.getIdUser());

        // SELECT
        Optional<UsersEntity> selectedUserOpt = userRepository.findById(user.getIdUser());
        assertTrue(selectedUserOpt.isPresent());
        UsersEntity selectedUser = selectedUserOpt.get();
        assertEquals(2, selectedUser.getDepartments().size());
        assertTrue(selectedUser.getDepartments().contains(department1));
        assertTrue(selectedUser.getDepartments().contains(department2));

        // DELETE
        userRepository.delete(selectedUser);
        assertFalse(userRepository.existsById(selectedUser.getIdUser()));
    }
}
