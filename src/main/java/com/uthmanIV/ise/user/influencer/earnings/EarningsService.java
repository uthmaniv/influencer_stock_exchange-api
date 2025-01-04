package com.uthmanIV.ise.user.influencer.earnings;

import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.UserRepository;
import com.uthmanIV.ise.user.stock.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EarningService {

    private final EarningsRepository earningsRepository;
    private final EarningsMapper earningsMapper;
    private final UserRepository userRepository;

    private List<EarningsDto> getAllEarnings(User user){
        return earningsMapper
                .toDtoList(earningsRepository.findByUserId(user.getId()));
    }

    private void addEarnings(User user, BigDecimal numberOfShares, Stock stock){
        //earning per share method will be called passing the numberOfShares
        Earnings userEarning = new Earnings();
        userEarning.setUser(
                userRepository.findById(user.getId())
                        .orElseThrow(()-> new ResourceNotFoundException("User not found")));
        userEarning.setEarningPerShare(numberOfShares.add(BigDecimal.TEN)); // will be refactored
        userEarning.setDate(LocalDateTime.now());
        userEarning.setTradingVolume(userEarning.getTradingVolume().add(BigDecimal.ONE));
        userEarning.setNetIncome(stock.getCurrentPrice().multiply(numberOfShares)); // will be refactored to use earningPerShare * numberOfShare
        earningsRepository.save(userEarning);
    }
}
