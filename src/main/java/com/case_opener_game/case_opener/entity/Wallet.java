package com.case_opener_game.case_opener.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(value = TemporalType.DATE)
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    @Temporal(value = TemporalType.DATE)
    private LocalDate updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id)
                && Objects.equals(balance, wallet.balance)
                && Objects.equals(createdAt, wallet.createdAt)
                && Objects.equals(updatedAt, wallet.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, createdAt, updatedAt);
    }
}
