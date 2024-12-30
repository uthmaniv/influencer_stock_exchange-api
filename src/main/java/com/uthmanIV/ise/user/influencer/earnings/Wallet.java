package com.uthmanIV.ise.user.influencer.earnings;

import com.uthmanIV.ise.user.influencer.Influencer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "influencer_wallet")
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "influencer_id")
    private Influencer influencer;

    @Column(name = "earnings")
    private BigDecimal earnings;

    @Column(name = "wallet_balance")
    private BigDecimal walletBalance;



}
