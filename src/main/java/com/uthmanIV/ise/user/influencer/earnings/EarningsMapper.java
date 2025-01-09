package com.uthmanIV.ise.user.influencer.earnings;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EarningsMapper {

    EarningsDto toDto(Earnings earnings);
    List<EarningsDto> toDtoList(List<Earnings> earnings);
}
