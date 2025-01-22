package com.vfaber.personalfinancemanager.exceptions;

import java.util.UUID;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException() {
        super("Transaction Not Found");
    }

    public TransactionNotFoundException(UUID id) {
        super("Transaction: " + id + " Not Found");
    }
}
