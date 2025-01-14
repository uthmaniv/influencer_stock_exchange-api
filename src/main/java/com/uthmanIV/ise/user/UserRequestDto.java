package com.uthmanIV.ise.user;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;


public record UserRequestDto(@NotNull @Email String email,
                             @NotBlank @Size(min=4) String password,
                             String pictureUrl,
                             @NotBlank String firstName,
                             @NotBlank String lastName,
                             @NotNull UserType userType) {
}
