package com.uthmanIV.ise.stock;

import com.uthmanIV.ise.user.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;


@Mapper(componentModel = "spring", uses = {UserService.class,StockService.class})
public interface StockMapper {

    @Mapping(source = "influencer.user", target = "influencerName", qualifiedByName = "userFullName")
    @Mapping(source = "influencer", target = "stockSymbol",qualifiedByName = "stockSymbolFromInfluencer")
    @Mapping(source = "influencer.user", target = "pictureUrl",qualifiedByName = "userPictureUrl")
    StockResponseDto toDto(Stock stock);


    List<StockResponseDto> toDtoList(Set<Stock> stocks);
}


