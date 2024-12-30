package com.uthmanIV.ise.user.investor.wallet;

import com.uthmanIV.ise.user.investor.Investor;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "investor_id")
    @NotNull
    private Investor investor;

    @Column(name = "wallet_balance")
    @NotNull
    private BigDecimal walletBalance;

}
