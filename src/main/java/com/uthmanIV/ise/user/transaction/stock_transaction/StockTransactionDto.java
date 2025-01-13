package com.uthmanIV.ise.user.transaction.stock_transaction;

import com.uthmanIV.ise.user.transaction.TransactionStatus;
import com.uthmanIV.ise.user.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockTransactionDto(LocalDateTime localDateTime,
                                  TransactionType transactionType,
                                  String stockSymbol,
                                  BigDecimal sharesBought,
                                  BigDecimal price,
                                  BigDecimal amount,
                                  TransactionStatus transactionStatus) {}