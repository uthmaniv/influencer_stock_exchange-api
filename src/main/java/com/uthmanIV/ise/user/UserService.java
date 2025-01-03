package com.uthmanIV.ise.user;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Named("userFullName")
    public String getUserFullName(User user){
        return user.getFirstName().concat(" " + user.getLastName());
    }

    @Named("userPictureUrl")
    public String getUserPictureUrl(User user){
        return user.getPictureUrl();
    }
}
