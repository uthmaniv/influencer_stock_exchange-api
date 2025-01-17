package com.uthmanIV.ise.user.influencer;

import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InfluencerService {

    private final InfluencerRepository influencerRepository;
    private final UserRepository userRepository;

    public InfluencerDto updateInfluencer(InfluencerDto requestDto){
        if(userRepository.existsByEmail(requestDto.email())){
            User user = userRepository.findByEmail(requestDto.email())
                    .orElseThrow(()-> new ResourceNotFoundException("User Not found with email"+ requestDto.email()));

            Influencer updatedInfluencer = influencerRepository.findByUserId(user.getId())
                    .orElseThrow(()-> new ResourceNotFoundException("User Not found with Id : "+ user.getId()));
            updatedInfluencer.setPhoneNumber(requestDto.phoneNumber());
            updatedInfluencer.setCity(requestDto.city());
            updatedInfluencer.setInfluencerType(requestDto.influencerType());
            updatedInfluencer.setInfluencerTier(requestDto.influencerTier());
            updatedInfluencer.setEthnicity(requestDto.ethnicity());
            updatedInfluencer.setLanguage(requestDto.language());

            influencerRepository.save(updatedInfluencer);
        }

        throw new ResourceNotFoundException("User not found");
    }

    public String influencerStockSymbol(String firstName, String lastName){
        StringBuilder stockSymbol = new StringBuilder();
        stockSymbol.append(firstName.charAt(0));
        stockSymbol.append(firstName.charAt(2));
        stockSymbol.append(lastName.charAt(0));
        stockSymbol.append(lastName.charAt(2));

        //might add additional check for the influencer by stockSymbol
        return stockSymbol.toString().toUpperCase();
    }


}
