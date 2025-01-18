package com.uthmanIV.ise.user.transaction.stock_transaction;

import com.uthmanIV.ise.user.stock.StockService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = StockService.class)
public interface StockTransactionMapper {

    @Mapping(source = "stock.influencer", target = "stockSymbol", qualifiedByName = "stockSymbolFromStock")
    StockTransactionDto toDto(StockTransaction stockTransaction);

    List<StockTransactionDto> toDtoList(List<StockTransaction> stockTransactions);
}
