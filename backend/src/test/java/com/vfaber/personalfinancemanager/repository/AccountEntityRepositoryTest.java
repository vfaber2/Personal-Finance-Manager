package com.vfaber.personalfinancemanager.repository;

import com.vfaber.personalfinancemanager.entity.AccountEntity;
import com.vfaber.personalfinancemanager.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.sql.init.mode=never"
})
class AccountEntityRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAccountEntityWithUser() {
        // Arrange
        UserEntity user = UserEntity.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password123")
                .build();
        UserEntity savedUser = userRepository.save(user); // Database generates the ID

        AccountEntity account = AccountEntity.builder()
                .uuid(UUID.randomUUID())
                .IBAN("DE12345678901234567890")
                .accountHolder(savedUser)
                .balance(1000.00)
                .build();

        // Act
        AccountEntity savedAccount = accountRepository.save(account);

        // Assert
        assertNotNull(savedAccount.getUuid());
        assertEquals("DE12345678901234567890", savedAccount.getIBAN());
        assertEquals(1000.00, savedAccount.getBalance());
        assertEquals(savedUser, savedAccount.getAccountHolder());
    }

    @Test
    void testFindByAccountHolder() {
        // Arrange
        UserEntity user = UserEntity.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password123")
                .build();
        UserEntity savedUser = userRepository.save(user);

        AccountEntity account = AccountEntity.builder()
                .uuid(UUID.randomUUID())
                .IBAN("DE12345678901234567890")
                .accountHolder(savedUser)
                .balance(1000.00)
                .build();
        accountRepository.save(account);

        // Act
        Optional<AccountEntity> foundAccount = accountRepository.findByAccountHolder(savedUser);

        // Assert
        assertTrue(foundAccount.isPresent());
        assertEquals("DE12345678901234567890", foundAccount.get().getIBAN());
        assertEquals(savedUser, foundAccount.get().getAccountHolder());
    }

    @Test
    void testFindByAccountHolderNotFound() {
        // Arrange
        UserEntity user = UserEntity.builder()
                .username("nonexistentuser")
                .email("nonexistent@example.com")
                .password("password123")
                .build();
        UserEntity savedUser = userRepository.save(user);

        // Act
        Optional<AccountEntity> foundAccount = accountRepository.findByAccountHolder(savedUser);

        // Assert
        assertFalse(foundAccount.isPresent());
    }

    @Test
    void testFindByIBAN() {
        // Arrange
        AccountEntity account = AccountEntity.builder()
                .uuid(UUID.randomUUID())
                .IBAN("DE12345678901234567890")
                .balance(1000.00)
                .build();
        accountRepository.save(account);

        // Act
        Optional<AccountEntity> foundAccount = accountRepository.findByIBAN("DE12345678901234567890");

        // Assert
        assertTrue(foundAccount.isPresent());
        assertEquals(1000.00, foundAccount.get().getBalance());
    }

    @Test
    void testFindByIBANNotFound() {
        // Act
        Optional<AccountEntity> foundAccount = accountRepository.findByIBAN("NONEXISTENTIBAN");

        // Assert
        assertFalse(foundAccount.isPresent());
    }

}
