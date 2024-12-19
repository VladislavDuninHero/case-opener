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
@Table(name = "game_rounds")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "multiplier")
    private BigDecimal multiplier;

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
