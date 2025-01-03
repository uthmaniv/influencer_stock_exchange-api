package com.uthmanIV.ise.user.influencer.earnings;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EarningsDto(LocalDateTime date,
                          BigDecimal earningPerShare,
                          BigDecimal tradingVolume,
                          BigDecimal netIncome){}
