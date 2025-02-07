package com.uthmanIV.ise.influencer;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InfluencerDto(@NotBlank String pictureUrl,
                            @NotNull @Email String email,
                            @NotBlank @Size(max=4) String stockSymbol,
                            @NotBlank String phoneNumber,
                            @NotBlank @Size(min = 3) String country,
                            @NotBlank String state,
                            @NotBlank String city,
                            @NotNull @Enumerated InfluencerType influencerType,
                            @NotNull @Enumerated InfluencerTier influencerTier,
                            @NotBlank @Size(min = 4) String ethnicity,
                            @NotBlank String language) {}
