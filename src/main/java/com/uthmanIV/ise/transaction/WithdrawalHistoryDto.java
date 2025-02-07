package com.uthmanIV.ise.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record WithdrawalHistoryDto(LocalDateTime dateTime,
                                   TransactionType transactionType,
                                   String description,
                                   BigDecimal amount,
                                   TransactionStatus withdrawalStatus) {
}
