package com.uthmanIV.ise.user.stock.stock_transaction;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = StockTransactionService.class)
public interface StockTransactionMapper {

    @Mapping(source = "stock.influencer", target = "stockSymbol", qualifiedByName = "stockSymbolFromStock")
    StockTransactionDto toDto(StockTransaction stockTransaction);

    List<StockTransactionDto> toDtoList(List<StockTransaction> stockTransactions);
}
