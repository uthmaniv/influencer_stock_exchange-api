package com.uthmanIV.ise.stock;

import com.uthmanIV.ise.influencer.Influencer;
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


    @Column(name = "current_price", nullable = false)
    @NotNull(message = "Current price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal currentPrice;

    @Column(name = "price_change_today", nullable = false)
    @NotNull(message = "Today's price change is required")
    private BigDecimal priceChangeToday;

    @Column(name = "weeklyPriceChange", nullable = false)
    @NotNull(message = "7-day price change is required")
    private BigDecimal weeklyPriceChange;

    @Column(name = "market_cap", nullable = false)
    @NotNull(message = "Market cap is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Market cap must be greater than 0")
    private BigDecimal marketCap;

    @Column(name = "last_updated", nullable = false)
    @NotNull(message = "Last updated timestamp is required")
    private LocalDateTime lastUpdated;

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
                ", currentPrice=" + currentPrice +
                ", priceChangeToday=" + priceChangeToday +
                ", priceChange7Days=" + weeklyPriceChange +
                ", marketCap=" + marketCap +
                '}';
    }
}
