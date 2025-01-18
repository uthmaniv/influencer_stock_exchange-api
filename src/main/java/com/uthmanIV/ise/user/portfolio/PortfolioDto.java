package com.uthmanIV.ise.user.portfolio;

import com.uthmanIV.ise.user.stock.StockResponseDto;

import java.math.BigDecimal;
import java.util.List;

public record PortfolioDto(BigDecimal portfolioValue,
                           List<StockResponseDto> stocksDto) {
}
