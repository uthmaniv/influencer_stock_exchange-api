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
    private final InfluencerMapper influencerMapper;

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
            userRepository.save(user);

            return influencerMapper.toDto(updatedInfluencer);
        }

        throw new ResourceNotFoundException("User not found");
    }

    public String influencerStockSymbol(String firstName, String lastName){
        String stockSymbol = String.valueOf(firstName.charAt(0)) +
                firstName.charAt(2) +
                lastName.charAt(0) +
                lastName.charAt(2);
        if(influencerRepository.existsByStockSymbol(stockSymbol)){
            stockSymbol= stockSymbol + "X";
        }
        //might add additional check for the influencer by stockSymbol
        return stockSymbol.toUpperCase();
    }


}
