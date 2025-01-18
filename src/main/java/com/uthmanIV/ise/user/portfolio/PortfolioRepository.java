package com.uthmanIV.ise.user.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {

    Optional<Portfolio> findByUserId(Long userId);

    @Query("SELECT SUM(ps.totalPrice) FROM PortfolioStock ps WHERE ps.portfolio.id = :portfolioId")
    BigDecimal getTotalPortfolioValue(@Param("portfolioId") Long portfolioId);

}
