package com.vfaber.personalfinancemanager.controller.impl;

import com.vfaber.personalfinancemanager.controller.AccountController;
import com.vfaber.personalfinancemanager.dto.AccountDto;
import com.vfaber.personalfinancemanager.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {
    private final AccountRepository accountRepository;

    @PostMapping("/addAccount")
    @Override
    public ResponseEntity<AccountDto> addAccount(AccountDto accountDto) {
        return null;
    }

    @PostMapping("/updateAccount/{IBAN}")
    @Override
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String IBAN) {
        return null;
    }

    @PostMapping("/deleteAccount/{IBAN}")
    @Override
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable String IBAN) {
        return null;
    }

    @PostMapping("/getAccount/{IBAN}")
    @Override
    public ResponseEntity<AccountDto> getAccount(@PathVariable String IBAN) {
        return null;
    }

    @PostMapping("/getAllAccounts")
    @Override
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return null;
    }
}
