package com.uthmanIV.ise.user.investor.wallet;

import com.uthmanIV.ise.user.stock.Stock;
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
@Table(name = "transaction_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat
    @Column(name = "date")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType transactionType;

    @OneToMany
    @JoinColumn(name = "stock")
    private Set<Stock> stocks;

    @Column(name = "no_of_shares",nullable = false)
    @NotNull
    private BigDecimal shareBought;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Column(name = "amount")
    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull
    private TransactionStatus transactionStatus;


}
