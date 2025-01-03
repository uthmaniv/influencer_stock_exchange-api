package com.uthmanIV.ise.user.influencer;

public record InfluencerDto(Long userId,
                            String stockSymbol,
                            String phoneNumber,
                            String country,
                            String state,
                            String city,
                            Gender gender
                            ) {}
