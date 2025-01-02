package com.uthmanIV.ise.user.influencer.earnings;

import com.uthmanIV.ise.user.wallet.Wallet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "earning_history")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EarningHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @DateTimeFormat
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "earning_per_share", nullable = false)
    private BigDecimal earningPerShare;

    @Column(name = "trading_volume", nullable = false)
    private BigDecimal tradingVolume;

    @Column(name = "net_income",nullable = false)
    private BigDecimal netIncome;


}
