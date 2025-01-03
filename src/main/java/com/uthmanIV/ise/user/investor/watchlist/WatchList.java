package com.uthmanIV.ise.user.investor.watchlist;

import com.uthmanIV.ise.user.investor.Investor;
import com.uthmanIV.ise.user.stock.Stock;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "watchlist")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    @ManyToMany
    @JoinTable(
            name = "watchlist_stock",
            joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    private Set<Stock> stocks;
}
