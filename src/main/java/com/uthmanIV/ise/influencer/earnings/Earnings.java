package com.uthmanIV.ise.influencer.earnings;

import com.uthmanIV.ise.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "earning_history")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Earnings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @DateTimeFormat
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "earning_per_share", nullable = false)
    private BigDecimal earningPerShare;

    @Column(name = "trading_volume", nullable = false)
    private BigDecimal tradingVolume;

    @Column(name = "net_income",nullable = false)
    private BigDecimal netIncome;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Earnings earnings = (Earnings) o;
        return getId() != null && Objects.equals(getId(), earnings.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "user = " + user + ", " +
                "date = " + date + ", " +
                "tradingVolume = " + tradingVolume + ")";
    }
}
