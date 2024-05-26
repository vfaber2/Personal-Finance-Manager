package com.vfaber.personalFinanceManager.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException() {
        super("User not found");
    }

    public UsernameNotFoundException(String username) {
        super("User: " + username + " not found");
    }
}
