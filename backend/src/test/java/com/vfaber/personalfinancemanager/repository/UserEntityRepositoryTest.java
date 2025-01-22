package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest(properties = {
        "spring.sql.init.mode=never"
})
class UserEntityRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setUsername("test1");
        user.setPassword("password123");
        user.setFirstName("testFirstName");
        user.setLastName("testLastName");
        user.setEmail("test.email@gmail.com");

        // Act
        UserEntity savedUser = userRepository.save(user);

        // Assert
        assertNotNull(savedUser.getId());
        assertEquals("test1", savedUser.getUsername());
    }

    @Test
    void testUniqueEmailConstraint() {
        // Arrange
        UserEntity user1 = new UserEntity();
        user1.setUsername("user1");
        user1.setPassword("password123");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("uniqueTest@gmail.com");

        UserEntity user2 = new UserEntity();
        user2.setUsername("user2");
        user2.setPassword("password123");
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setEmail("uniqueTest@gmail.com"); // Same email as user1

        // Act
        userRepository.save(user1);
        Exception exception = assertThrows(Exception.class, () -> userRepository.save(user2));

        // Assert
        assertTrue(exception.getMessage().contains("Unique index or primary key violation"));
    }

    @Test
    void testFindById() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setUsername("test1");
        user.setPassword("password123");
        user.setFirstName("testFirstName");
        user.setLastName("testLastName");
        user.setEmail("test.email@gmail.com");
        UserEntity savedUser = userRepository.save(user);

        // Act
        UserEntity foundUser = userRepository.findById(savedUser.getId()).orElse(null);

        // Assert
        assertNotNull(foundUser);
        assertEquals("test1", foundUser.getUsername());
    }

    @Test
    void testDeleteUser() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setUsername("test1");
        user.setPassword("password123");
        user.setFirstName("testFirstName");
        user.setLastName("testLastName");
        user.setEmail("test.email@gmail.com");
        UserEntity savedUser = userRepository.save(user);

        // Act
        userRepository.delete(savedUser);

        // Assert
        assertFalse(userRepository.findById(savedUser.getId()).isPresent());
    }

    @Test
    void testFindByUsername() {
        // Arrange
        UserEntity user = new UserEntity();
        user.setUsername("user1");
        user.setPassword("password123");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@gmail.com");
        userRepository.save(user);

        // Act
        Optional<UserEntity> foundUser = userRepository.findByUsername("user1");

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals("user1", foundUser.get().getUsername());
        assertEquals("John", foundUser.get().getFirstName());
    }

    @Test
    void testFindByUsernameNotFound() {
        // Act
        Optional<UserEntity> foundUser = userRepository.findByUsername("nonexistent");

        // Assert
        assertFalse(foundUser.isPresent());
    }

    @Test
    void testFindAll() {
        // Arrange
        UserEntity user1 = new UserEntity();
        user1.setUsername("user1");
        user1.setPassword("password123");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@gmail.com");

        UserEntity user2 = new UserEntity();
        user2.setUsername("user2");
        user2.setPassword("password456");
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setEmail("jane.smith@gmail.com");

        userRepository.save(user1);
        userRepository.save(user2);

        // Act
        List<UserEntity> users = userRepository.findAll();

        // Assert
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void testFindAllEmpty() {
        // Act
        List<UserEntity> users = userRepository.findAll();

        // Assert
        assertNotNull(users);
        assertTrue(users.isEmpty());
    }
}
