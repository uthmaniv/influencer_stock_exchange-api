package com.uthmanIV.ise.user.stock;

import java.math.BigDecimal;

public record StockResponseDto(String pictureUrl,
                               String influencerName,
                               String stockSymbol,
                               BigDecimal currentPrice,
                               BigDecimal priceChangeToday,
                               BigDecimal weeklyPriceChange,
                               BigDecimal marketCap
                               ) {}
