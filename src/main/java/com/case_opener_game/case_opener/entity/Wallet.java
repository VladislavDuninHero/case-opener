package com.case_opener_game.case_opener.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

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
}
