package com.uthmanIV.ise.portfolio;

import com.uthmanIV.ise.stock.Stock;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "portfolio_stock")
@Getter
@Setter
public class PortfolioStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(name = "shares", nullable = false)
    private BigDecimal numberOfShares;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;


}