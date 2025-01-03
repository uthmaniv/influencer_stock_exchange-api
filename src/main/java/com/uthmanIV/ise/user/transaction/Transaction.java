package com.uthmanIV.ise.user.transaction;

import com.uthmanIV.ise.user.wallet.Wallet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet; // Link to the wallet performing the transaction

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType; // Enum: DEPOSIT, WITHDRAWAL, TRADE

    @Column(name = "amount", nullable = false)
    private BigDecimal amount; // Amount involved in the transaction

    @Column(name = "description")
    private String description; // Optional description (e.g., withdrawal reason, trade details)

    @Column(name = "date", nullable = false)
    private LocalDateTime date; // Timestamp of the transaction

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status; // Enum: PENDING, COMPLETED, FAILED
}

