package com.uthmanIV.ise.user.transaction.stock_transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockTransactionRepository extends JpaRepository<StockTransaction,Long> {

    Optional<List<StockTransaction>> findByUserId(Long userId);
}
