package com.vfaber.personalfinancemanager.service;

import com.vfaber.personalfinancemanager.dto.AccountDto;
import com.vfaber.personalfinancemanager.dto.TransactionDto;
import com.vfaber.personalfinancemanager.dto.UserDto;
import com.vfaber.personalfinancemanager.entity.AccountEntity;
import com.vfaber.personalfinancemanager.entity.TransactionEntity;
import com.vfaber.personalfinancemanager.entity.UserEntity;

public class Mapper {

    public UserDto entityToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        return userDto;
    }

    public AccountDto entityToDto(AccountEntity accountEntity) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(accountEntity.getUuid());
        accountDto.setAccountHolder(entityToDto(accountEntity.getAccountHolder()));
        accountDto.setBalance(accountEntity.getBalance());
        return accountDto;
    }

    public TransactionDto entityToDto(TransactionEntity transactionEntity) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId(transactionEntity.getTransactionId());
        transactionDto.setAmount(transactionEntity.getAmount());
        transactionDto.setAccountTo(entityToDto(transactionEntity.getAccountTo()));
        transactionDto.setAccountFrom(entityToDto(transactionEntity.getAccountFrom()));
        return transactionDto;
    }

    public UserEntity dtoToEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        return userEntity;
    }

    public AccountEntity dtoToEntity(AccountDto accountDto) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUuid(accountDto.getId());
        accountEntity.setAccountHolder(dtoToEntity(accountDto.getAccountHolder()));
        accountEntity.setBalance(accountDto.getBalance());
        return accountEntity;
    }

    public TransactionEntity dtoToEntity(TransactionDto transactionDto) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(transactionDto.getTransactionId());
        transactionEntity.setAmount(transactionDto.getAmount());
        transactionEntity.setAccountTo(dtoToEntity(transactionDto.getAccountTo()));
        transactionEntity.setAccountFrom(dtoToEntity(transactionDto.getAccountFrom()));
        return transactionEntity;
    }
}
