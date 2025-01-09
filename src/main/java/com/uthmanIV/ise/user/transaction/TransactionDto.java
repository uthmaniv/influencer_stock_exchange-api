package com.uthmanIV.ise.user.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto(TransactionType transactionType,
                             BigDecimal amount,
                             String description,
                             LocalDateTime date,
                             TransactionStatus status) {
}
