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
        UserEntity user = UserEntity.builder()
                .username("test1")
                .password("password123")
                .firstName("testFirstName")
                .lastName("testLastName")
                .email("test.email@gmail.com")
                .build();

        // Act
        UserEntity savedUser = userRepository.save(user);

        // Assert
        assertNotNull(savedUser.getId());
        assertEquals("test1", savedUser.getUsername());
    }

    @Test
    void testUniqueEmailConstraint() {
        // Arrange
        UserEntity user1 = UserEntity.builder()
                .username("user1")
                .password("password123")
                .firstName("John")
                .lastName("Doe")
                .email("uniqueTest@gmail.com")
                .build();

        UserEntity user2 = UserEntity.builder()
                .username("user2")
                .password("password123")
                .firstName("Jane")
                .lastName("Smith")
                .email("uniqueTest@gmail.com") // Same email as user1
                .build();

        // Act
        userRepository.save(user1);
        Exception exception = assertThrows(Exception.class, () -> userRepository.save(user2));

        // Assert
        assertTrue(exception.getMessage().contains("Unique index or primary key violation"));
    }

    @Test
    void testFindById() {
        // Arrange
        UserEntity user = UserEntity.builder()
                .username("test1")
                .password("password123")
                .firstName("testFirstName")
                .lastName("testLastName")
                .email("test.email@gmail.com")
                .build();
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
        UserEntity user = UserEntity.builder()
                .username("test1")
                .password("password123")
                .firstName("testFirstName")
                .lastName("testLastName")
                .email("test.email@gmail.com")
                .build();
        UserEntity savedUser = userRepository.save(user);

        // Act
        userRepository.delete(savedUser);

        // Assert
        assertFalse(userRepository.findById(savedUser.getId()).isPresent());
    }

    @Test
    void testFindByUsername() {
        // Arrange
        UserEntity user = UserEntity.builder()
                .username("user1")
                .password("password123")
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@gmail.com")
                .build();
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
        UserEntity user1 = UserEntity.builder()
                .username("user1")
                .password("password123")
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@gmail.com")
                .build();

        UserEntity user2 = UserEntity.builder()
                .username("user2")
                .password("password456")
                .firstName("Jane")
                .lastName("Smith")
                .email("jane.smith@gmail.com")
                .build();

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
