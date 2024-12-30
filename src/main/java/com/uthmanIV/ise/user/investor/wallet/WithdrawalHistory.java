package com.uthmanIV.ise.user.investor.wallet;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "withdrawal_history")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WithdrawalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @DateTimeFormat
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",nullable = false)
    private TransactionType transactionType;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "amount")
    @NotNull
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private TransactionStatus withdrawalStatus;


}
