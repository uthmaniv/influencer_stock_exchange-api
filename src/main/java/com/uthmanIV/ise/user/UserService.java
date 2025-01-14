package com.uthmanIV.ise.user;

import com.uthmanIV.ise.exceptions.UserAlreadyExistException;
import com.uthmanIV.ise.user.portfolio.Portfolio;
import com.uthmanIV.ise.user.wallet.Wallet;
import com.uthmanIV.ise.user.watchlist.WatchList;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    @Named("userPortfolioValue")
    public BigDecimal portfolioValue(User user){
        return user.getPortfolio().getStockValue();
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

            portfolio.setUser(newUser);
            watchList.setUser(newUser);
            wallet.setUser(newUser);
            wallet.setWalletBalance(BigDecimal.ZERO);

            newUser.setPortfolio(portfolio);
            newUser.setWatchList(watchList);
            newUser.setWallet(wallet);

            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistException("User with email " + userRequestDto.email() + " already exists");
        }
    }

}
