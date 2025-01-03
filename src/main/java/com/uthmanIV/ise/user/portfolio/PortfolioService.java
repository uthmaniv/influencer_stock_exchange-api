package com.uthmanIV.ise.user.investor.portfolio;

import com.uthmanIV.ise.exceptions.InsufficientFundsException;
import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.investor.Investor;
import com.uthmanIV.ise.user.stock.Stock;
import com.uthmanIV.ise.user.stock.StockMapper;
import com.uthmanIV.ise.user.stock.StockResponseDto;
import com.uthmanIV.ise.user.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final StockMapper stockMapper;

    public List<StockResponseDto> getPortfolioStocks(Investor investor){
        Portfolio portfolio = portfolioRepository
                .findById(investor.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Portfolio Not Found"));
        return stockMapper
                .toDtoList(portfolio.getStocks());
    }

    public void buyStock(User user,BigDecimal numberOfShares, Stock stock){
        BigDecimal stockPrice = numberOfShares .multiply(stock.getCurrentPrice());
        if (!validateBuyingPower(user, stockPrice)) {
            throw new InsufficientFundsException("Insufficient funds to complete the purchase.");
        }
        //validates the buying power of the investor in respect to the (stockprice * no_shares)
        //if valid, adds the stock to the investor's portfolio and subtract the amount from his buying power
        //update his stock value (to add up the stock value to his total stock value)
        //updates the influencer's (stock bought) earnings

    }

    public boolean validateBuyingPower(User user, BigDecimal stockPrice) {
        Wallet userWallet = user.getWallet();
        BigDecimal newBalance = userWallet.getWalletBalance().subtract(stockPrice);

        // Check if newBalance is greater than or equal to 0
        //returns -1 if insufficient funds
        return newBalance.compareTo(BigDecimal.ZERO) >= 0;
    }

}
