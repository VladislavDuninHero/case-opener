package com.case_opener_game.case_opener.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "game_rounds")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "multiplier")
    private Integer multiplier;

    @Column(name = "payout")
    private BigDecimal payout;

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate updatedAt;
}