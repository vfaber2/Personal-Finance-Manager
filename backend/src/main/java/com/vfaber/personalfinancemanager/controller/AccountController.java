package com.vfaber.personalfinancemanager.controller;

import com.vfaber.personalfinancemanager.dto.AccountDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountController {

    ResponseEntity<AccountDto> addAccount(AccountDto accountDto);

    ResponseEntity<AccountDto> updateAccount(String IBAN);

    ResponseEntity<AccountDto> deleteAccount(String IBAN);

    ResponseEntity<AccountDto> getAccount(String IBAN);

    ResponseEntity<List<AccountDto>> getAllAccounts();
}
