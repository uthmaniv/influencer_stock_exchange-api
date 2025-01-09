package com.uthmanIV.ise.user.transaction;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    WithdrawalHistoryDto toDto(WithdrawalHistory withdrawalHistory);
    TransactionDto toDto(Transaction transaction);

    List<WithdrawalHistoryDto> withdrawalsDto(List<WithdrawalHistory> withdrawals);
    List<TransactionDto> toDtoList(List<Transaction> transactions);
}
