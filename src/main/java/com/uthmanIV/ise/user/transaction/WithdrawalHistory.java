package com.uthmanIV.ise.user.transaction;

import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.wallet.Wallet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private TransactionStatus withdrawalStatus;

}
