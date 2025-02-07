package com.uthmanIV.ise.user;

import com.uthmanIV.ise.exceptions.UserAlreadyExistException;
import com.uthmanIV.ise.influencer.Influencer;
import com.uthmanIV.ise.influencer.InfluencerRepository;
import com.uthmanIV.ise.influencer.InfluencerService;
import com.uthmanIV.ise.investor.Investor;
import com.uthmanIV.ise.investor.InvestorRepository;
import com.uthmanIV.ise.portfolio.Portfolio;
import com.uthmanIV.ise.wallet.Wallet;
import com.uthmanIV.ise.watchlist.WatchList;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final InfluencerRepository influencerRepository;
    private final InvestorRepository investorRepository;
    private final InfluencerService influencerService;

    @Named("userFullName")
    public String getUserFullName(User user){
        return user.getFirstName().concat(" " + user.getLastName());
    }

    @Named("userPictureUrl")
    public String getUserPictureUrl(User user){
        return user.getPictureUrl();
    }

    public void registerUser(UserRequestDto userRequestDto) {
        try {
            User newUser = new User();
            Portfolio portfolio =new Portfolio();
            WatchList watchList = new WatchList();
            Wallet wallet = new Wallet();

            newUser.setEmail(userRequestDto.email());
            newUser.setPassword(userRequestDto.password());
            newUser.setFirstName(userRequestDto.firstName());
            newUser.setLastName(userRequestDto.lastName());
            newUser.setPictureUrl(userRequestDto.pictureUrl());
            newUser.setUserType(userRequestDto.userType());

            portfolio.setUser(newUser);
            watchList.setUser(newUser);
            wallet.setUser(newUser);
            wallet.setWalletBalance(BigDecimal.ZERO);

            newUser.setPortfolio(portfolio);
            newUser.setWatchList(watchList);
            newUser.setWallet(wallet);

            userRepository.save(newUser);
            associateAndSaveUserByType(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistException("User with email " + userRequestDto.email() + " already exists");
        }
    }

    public void associateAndSaveUserByType(User user){
        if (user.getUserType().equals(UserType.INFLUENCER)) {
            Influencer influencer = new Influencer();
            influencer.setUser(user);
            influencer.setStockSymbol(
                    influencerService.influencerStockSymbol(user.getFirstName(),user.getLastName()));
            //might require other initialization of fields for influencer
            influencerRepository.save(influencer);
        } else if (user.getUserType().equals(UserType.INVESTOR)) {
            Investor investor = new Investor();
            investor.setUser(user);
            //might require other initialization of fields for investor
            investorRepository.save(investor);
        }
    }


}
