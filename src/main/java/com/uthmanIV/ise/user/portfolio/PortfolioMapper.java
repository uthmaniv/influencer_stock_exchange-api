package com.uthmanIV.ise.user.portfolio;

import com.uthmanIV.ise.user.UserService;
import com.uthmanIV.ise.user.stock.StockMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PortfolioService.class, UserService.class, StockMapper.class})
public interface PortfolioMapper {

    @Mapping(source = "user.portfolio",
             target = "stockValue",
             qualifiedByName = "userPortfolioValue")
    @Mapping(source = "stocks", target = "stocksDto")
    PortfolioDto toDto(Portfolio portfolio);
}

