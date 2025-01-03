package com.uthmanIV.ise.user.influencer.earnings;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EarningsMapper {
    EarningsDto toDto(Earnings earnings);
}
