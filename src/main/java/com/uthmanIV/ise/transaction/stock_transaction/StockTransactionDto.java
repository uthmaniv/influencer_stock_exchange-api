package com.uthmanIV.ise.transaction.stock_transaction;

import com.uthmanIV.ise.transaction.TransactionStatus;
import com.uthmanIV.ise.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockTransactionDto(LocalDateTime localDateTime,
                                  TransactionType transactionType,
                                  String stockSymbol,
                                  BigDecimal sharesBought,
                                  BigDecimal price,
                                  BigDecimal amount,
                                  TransactionStatus transactionStatus) {}