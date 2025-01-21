package com.vfaber.personalfinancemanager.Repository;

import com.vfaber.personalfinancemanager.entity.AccountEntity;
import com.vfaber.personalfinancemanager.entity.TransactionEntity;
import com.vfaber.personalfinancemanager.entity.UserEntity;
import com.vfaber.personalfinancemanager.repository.AccountRepository;
import com.vfaber.personalfinancemanager.repository.TransactionRepository;
import com.vfaber.personalfinancemanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.sql.init.mode=never"
})
class TransactionEntityRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveTransaction() {
        // Arrange
        AccountEntity accountFrom = createAndSaveAccount("userFrom", "DE12345678901234567890");
        AccountEntity accountTo = createAndSaveAccount("userTo", "DE09876543210987654321");

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setAmount(500.00);
        transaction.setAccountFrom(accountFrom);
        transaction.setAccountTo(accountTo);

        // Act
        TransactionEntity savedTransaction = transactionRepository.save(transaction);

        // Assert
        assertNotNull(savedTransaction.getTransactionId());
        assertEquals(500.00, savedTransaction.getAmount());
        assertEquals(accountFrom, savedTransaction.getAccountFrom());
        assertEquals(accountTo, savedTransaction.getAccountTo());
    }

    @Test
    void testFindTransactionById() {
        // Arrange
        TransactionEntity savedTransaction = saveTransaction();

        // Act
        Optional<TransactionEntity> foundTransaction = transactionRepository.findById(savedTransaction.getTransactionId());

        // Assert
        assertTrue(foundTransaction.isPresent());
        assertEquals(savedTransaction.getTransactionId(), foundTransaction.get().getTransactionId());
    }

    @Test
    void testFindTransactionByIdNotFound() {
        // Act
        Optional<TransactionEntity> foundTransaction = transactionRepository.findById(UUID.randomUUID());

        // Assert
        assertFalse(foundTransaction.isPresent());
    }

    @Test
    void testFindAllByAccountFrom() {
        // Arrange
        AccountEntity accountFrom = createAndSaveAccount("userFrom", "DE12345678901234567890");
        AccountEntity accountTo = createAndSaveAccount("userTo", "DE09876543210987654321");

        saveTransaction(accountFrom, accountTo, 500.00);
        saveTransaction(accountFrom, accountTo, 300.00);

        // Act
        Optional<List<TransactionEntity>> transactions = transactionRepository.findAllByAccountFrom(accountFrom);

        // Assert
        assertTrue(transactions.isPresent());
        assertEquals(2, transactions.get().size());
    }

    @Test
    void testFindAllByAccountTo() {
        // Arrange
        AccountEntity accountFrom = createAndSaveAccount("userFrom", "DE12345678901234567890");
        AccountEntity accountTo = createAndSaveAccount("userTo", "DE09876543210987654321");

        saveTransaction(accountFrom, accountTo, 200.00);
        saveTransaction(accountFrom, accountTo, 100.00);

        // Act
        Optional<List<TransactionEntity>> transactions = transactionRepository.findAllByAccountTo(accountTo);

        // Assert
        assertTrue(transactions.isPresent());
        assertEquals(2, transactions.get().size());
    }

    @Test
    void testFindAllByAccountFromNotFound() {
        // Arrange
        AccountEntity accountFrom = createAndSaveAccount("userFrom", "DE12345678901234567890");

        // Act
        Optional<List<TransactionEntity>> transactions = transactionRepository.findAllByAccountFrom(accountFrom);

        // Assert
        assertTrue(transactions.isPresent());
        assertTrue(transactions.get().isEmpty());
    }

    @Test
    void testFindAllByAccountToNotFound() {
        // Arrange
        AccountEntity accountTo = createAndSaveAccount("userTo", "DE09876543210987654321");

        // Act
        Optional<List<TransactionEntity>> transactions = transactionRepository.findAllByAccountTo(accountTo);

        // Assert
        assertTrue(transactions.isPresent());
        assertTrue(transactions.get().isEmpty());
    }

    // Utility Methods
    private AccountEntity createAndSaveAccount(String username, String iban) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(username + "@example.com");
        user.setPassword("password123");
        UserEntity savedUser = userRepository.save(user);

        AccountEntity account = new AccountEntity();
        account.setUuid(UUID.randomUUID());
        account.setIBAN(iban);
        account.setBalance(1000.00);
        account.setAccountHolder(savedUser);
        return accountRepository.save(account);
    }

    private TransactionEntity saveTransaction() {
        AccountEntity accountFrom = createAndSaveAccount("userFrom", "DE12345678901234567890");
        AccountEntity accountTo = createAndSaveAccount("userTo", "DE09876543210987654321");
        return saveTransaction(accountFrom, accountTo, 500.00);
    }

    private TransactionEntity saveTransaction(AccountEntity accountFrom, AccountEntity accountTo, double amount) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setAmount(amount);
        transaction.setAccountFrom(accountFrom);
        transaction.setAccountTo(accountTo);
        return transactionRepository.save(transaction);
    }
}
