package com.uthmanIV.ise.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto(TransactionType transactionType,
                             BigDecimal amount,
                             String description,
                             LocalDateTime date,
                             TransactionStatus status) {
}
