package com.uthmanIV.ise.portfolio;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PortfolioService.class)
public interface PortfolioMapper {

    @Mapping(source = "user.portfolio.id",
             target = "portfolioValue",
             qualifiedByName = "portfolioValue")
    PortfolioDto toDto(Portfolio portfolio);
}


