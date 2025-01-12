package com.uthmanIV.ise.user.stock.stock_transaction;

import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.stock.Stock;
import com.uthmanIV.ise.user.transaction.TransactionStatus;
import com.uthmanIV.ise.user.transaction.TransactionType;
import com.uthmanIV.ise.user.wallet.Wallet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "stock_transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(name = "no_of_shares",nullable = false)
    @NotNull
    private BigDecimal numberOfShares;

    @Column(name = "stock_unit_price")
    @NotNull
    private BigDecimal stockUnitPrice;

    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull
    private TransactionStatus transactionStatus;
}
