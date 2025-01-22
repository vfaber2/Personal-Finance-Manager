package com.vfaber.personalfinancemanager.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException() {
        super("Account not found");
    }

    public AccountNotFoundException(String IBAN) {
        super("Account:" + IBAN + " not found");
    }


}
