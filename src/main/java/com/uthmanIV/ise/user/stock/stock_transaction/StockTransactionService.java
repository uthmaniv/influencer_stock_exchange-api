package com.uthmanIV.ise.user.stock.stock_transaction;

import com.uthmanIV.ise.exceptions.InsufficientFundsException;
import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.UserRepository;
import com.uthmanIV.ise.user.influencer.earnings.EarningsService;
import com.uthmanIV.ise.user.portfolio.PortfolioService;
import com.uthmanIV.ise.user.stock.Stock;
import com.uthmanIV.ise.user.stock.StockRepository;
import com.uthmanIV.ise.user.wallet.Wallet;
import com.uthmanIV.ise.user.wallet.WalletRepository;
import com.uthmanIV.ise.user.wallet.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class StockTransactionService {

    private final WalletService walletService;
    private final PortfolioService portfolioService;
    private final WalletRepository walletRepository;
    private final EarningsService earningsService;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    @Transactional
    public void buyStock(Long userId, BigDecimal numberOfShares, Long stockId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with id: "+userId + " not found" ));
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(()-> new ResourceNotFoundException("Stock with id: "+ stockId + " not found"));
        BigDecimal stockPrice = numberOfShares
                .multiply(stock.getCurrentPrice());
        if (!walletService.hasSufficientFunds(user, stockPrice)) {
            throw new InsufficientFundsException("Insufficient funds to complete the purchase.");
        }

        walletService.updateWalletBalance(user.getWallet(),stockPrice,"ADD");
        portfolioService.updateUserPortfolioValue(user,"BUY", stockPrice); // move this to buyStockInPortfolio
        portfolioService.buyStock(userId,stockId);
        earningsService.addEarnings(user,numberOfShares,stock);//update influencer earning
        walletRepository.save(user.getWallet());
    }

    @Transactional
    public void sellStock(Long userId, BigDecimal numberOfShares, Long stockId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with id: "+userId + " not found" ));
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(()-> new ResourceNotFoundException("Stock with id: "+ stockId + " not found"));
        BigDecimal stockPrice = numberOfShares.multiply(stock.getCurrentPrice());
        Wallet wallet = user.getWallet();
        walletService.updateWalletBalance(wallet,stockPrice,"SUBTRACT");
        portfolioService.updateUserPortfolioValue(user,"SELL", stockPrice);
        portfolioService.removeStockFromPortfolio(user, stock);
        walletRepository.save(wallet);
    }
}
