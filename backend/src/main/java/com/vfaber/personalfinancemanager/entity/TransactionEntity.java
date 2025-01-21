package com.vfaber.personalfinancemanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Objects;
import java.util.UUID;


@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
public class TransactionEntity {
    @Id
    private final UUID transactionId;
    private final double amount;
    @ManyToOne
    @JoinColumn(name = "account_from")
    private final AccountEntity accountFrom;
    @ManyToOne
    @JoinColumn(name = "account_to")
    private final AccountEntity accountTo;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(transactionId, that.transactionId) && Objects.equals(accountFrom, that.accountFrom) && Objects.equals(accountTo, that.accountTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, accountFrom, accountTo);
    }
}
