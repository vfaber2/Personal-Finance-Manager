package com.vfaber.personalfinancemanager.service;

import java.util.UUID;

public interface TransferService {
    void transfer(UUID accountFromId, UUID accountToId, double amount);
}
