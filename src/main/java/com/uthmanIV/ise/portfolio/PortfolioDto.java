package com.uthmanIV.ise.portfolio;

import com.uthmanIV.ise.stock.StockResponseDto;

import java.math.BigDecimal;
import java.util.List;

public record PortfolioDto(BigDecimal portfolioValue,
                           List<StockResponseDto> stocksDto) {
}
