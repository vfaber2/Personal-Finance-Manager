package com.vfaber.personalfinancemanager.controller.impl;

import com.vfaber.personalfinancemanager.controller.TransactionController;
import com.vfaber.personalfinancemanager.dto.TransactionDto;
import com.vfaber.personalfinancemanager.exceptions.TransactionNotFoundException;
import com.vfaber.personalfinancemanager.repository.TransactionRepository;
import com.vfaber.personalfinancemanager.service.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class TransactionControllerImpl implements TransactionController {

    private final TransactionRepository transactionRepository;
    private final Mapper mapper = new Mapper();


    @PostMapping("/addTransaction")
    @Override
    public ResponseEntity<TransactionDto> addTransaction(@RequestBody TransactionDto transactionDto) {
        if (transactionRepository.existsById(transactionDto.getTransactionId())) {
            return ResponseEntity.notFound().build();
        }
        transactionRepository.save(mapper.dtoToEntity(transactionDto));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/updateTransaction")
    @Override
    public ResponseEntity<TransactionDto> updateTransaction(@RequestBody TransactionDto transactionDto) {
        if (transactionRepository.findById(transactionDto.getTransactionId()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        transactionRepository.save(mapper.dtoToEntity(transactionDto));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteTransaction/{id}")
    @Override
    public ResponseEntity<TransactionDto> deleteTransaction(@PathVariable UUID id) {
        if (transactionRepository.findById(id).isPresent()) {
            transactionRepository.delete(transactionRepository.findById(id).get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/getTransaction/{id}")
    @Override
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable UUID id) {
        try {
            TransactionDto transactionDto = mapper.entityToDto(
                    transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id))
            );
            return ResponseEntity.ok().body(transactionDto);
        } catch (TransactionNotFoundException _) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/getAllTransactions")
    @Override
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactionDtos = new ArrayList<>();
        transactionRepository.findAll().stream()
                .map(mapper::entityToDto)
                .forEach(transactionDtos::add);
        return ResponseEntity.ok().body(transactionDtos);
    }
}
