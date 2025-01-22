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
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        UserEntity savedUser = userRepository.save(user); // Database generates the ID

        AccountEntity account = new AccountEntity();
        account.setUuid(UUID.randomUUID());
        account.setIBAN("DE12345678901234567890");
        account.setAccountHolder(savedUser);
        account.setBalance(1000.00);

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
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        UserEntity savedUser = userRepository.save(user);

        AccountEntity account = new AccountEntity();
        account.setUuid(UUID.randomUUID());
        account.setIBAN("DE12345678901234567890");
        account.setAccountHolder(savedUser);
        account.setBalance(1000.00);
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
        UserEntity user = new UserEntity();
        user.setUsername("nonexistentuser");
        user.setEmail("nonexistent@example.com");
        user.setPassword("password123");
        UserEntity savedUser = userRepository.save(user);

        // Act
        Optional<AccountEntity> foundAccount = accountRepository.findByAccountHolder(savedUser);

        // Assert
        assertFalse(foundAccount.isPresent());
    }

    @Test
    void testFindByIBAN() {
        // Arrange
        AccountEntity account = new AccountEntity();
        account.setUuid(UUID.randomUUID());
        account.setIBAN("DE12345678901234567890");
        account.setBalance(1000.00);
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
