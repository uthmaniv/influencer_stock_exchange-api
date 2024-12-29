package com.uthmanIV.ise.user.stock;

import com.uthmanIV.ise.user.influencer.Influencer;
import com.uthmanIV.ise.user.investor.Portfolio;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "stock")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "influencer_id", nullable = false, unique = true)
    @NotNull(message = "Influencer association is required")
    private Influencer influencer;

    @Column(name = "stock_symbol", nullable = false, unique = true)
    @NotBlank(message = "Stock symbol is required")
    @Size(min = 1, max = 10, message = "Stock symbol must be between 1 and 10 characters")
    private String stockSymbol;

    @Column(name = "current_price", nullable = false)
    @NotNull(message = "Current price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal currentPrice;

    @Column(name = "price_change_today", nullable = false)
    @NotNull(message = "Today's price change is required")
    private BigDecimal priceChangeToday;

    @Column(name = "price_change_7days", nullable = false)
    @NotNull(message = "7-day price change is required")
    private BigDecimal priceChange7Days;

    @Column(name = "market_cap", nullable = false)
    @NotNull(message = "Market cap is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Market cap must be greater than 0")
    private BigDecimal marketCap;

    @Column(name = "tier", nullable = false)
    @NotBlank(message = "Tier is required")
    private String tier;

    @OneToOne
    @Column(name = "influencer_tier")
    private Influencer.InfluencerTier influencerTier;

    @Column(name = "last_updated", nullable = false)
    @NotNull(message = "Last updated timestamp is required")
    private LocalDateTime lastUpdated;

   @ManyToOne
   @JoinColumn(name = "portfolio_id")
   private Portfolio portfolio;

    @Column(name = "picture_url")
    private String pictureUrl; // Optional, used for card view display

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o)
                .getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Stock stock = (Stock) o;
        return id != null && Objects.equals(id, stock.id);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Stock{" +
                "influencer=" + influencer +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", currentPrice=" + currentPrice +
                ", priceChangeToday=" + priceChangeToday +
                ", priceChange7Days=" + priceChange7Days +
                ", marketCap=" + marketCap +
                ", tier='" + tier + '\'' +
                ", influencerTier=" + influencerTier +
                '}';
    }
}
