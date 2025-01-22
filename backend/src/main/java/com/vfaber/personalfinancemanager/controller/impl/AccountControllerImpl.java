package com.vfaber.personalfinancemanager.controller.impl;

import com.vfaber.personalfinancemanager.controller.AccountController;
import com.vfaber.personalfinancemanager.dto.AccountDto;
import com.vfaber.personalfinancemanager.exceptions.AccountNotFoundException;
import com.vfaber.personalfinancemanager.repository.AccountRepository;
import com.vfaber.personalfinancemanager.service.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {
    private final AccountRepository accountRepository;
    private final Mapper mapper = new Mapper();

    @PostMapping("/addAccount")
    @Override
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        if (accountRepository.existsById(accountDto.getId())) {
            return ResponseEntity.notFound().build();
        }
        accountRepository.save(mapper.dtoToEntity(accountDto));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/updateAccount")
    @Override
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDto) {
        if (accountRepository.findByIBAN(accountDto.getIBAN()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        accountRepository.save(mapper.dtoToEntity(accountDto));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteAccount/{IBAN}")
    @Override
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable String IBAN) {
        if (accountRepository.findByIBAN(IBAN).isPresent()) {
            accountRepository.delete(accountRepository.findByIBAN(IBAN).get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/getAccount/{IBAN}")
    @Override
    public ResponseEntity<AccountDto> getAccount(@PathVariable String IBAN) {

        try {
            AccountDto accountDto = mapper.entityToDto(accountRepository.findByIBAN(IBAN).orElseThrow(() -> new AccountNotFoundException(IBAN)));
            return ResponseEntity.ok().body(accountDto);
        } catch (AccountNotFoundException _) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/getAllAccounts")
    @Override
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDtos = new ArrayList<>();

        accountRepository.findAll().stream().map(mapper::entityToDto).forEach(accountDtos::add);

        return ResponseEntity.ok().body(accountDtos);
    }
}
