package com.uthmanIV.ise.influencer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InfluencerMapper {
    @Mapping(target = "email" , source = "user.email")
    @Mapping(target = "pictureUrl", source = "user.pictureUrl")
    InfluencerDto toDto(Influencer influencer);

    Influencer toEntity(InfluencerDto influencerDto);
}
