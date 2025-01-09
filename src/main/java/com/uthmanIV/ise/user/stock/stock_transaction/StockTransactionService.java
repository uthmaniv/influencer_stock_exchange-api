package com.uthmanIV.ise.user.stock.stock_transaction;

import com.uthmanIV.ise.exceptions.InsufficientFundsException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.influencer.earnings.EarningsService;
import com.uthmanIV.ise.user.portfolio.PortfolioService;
import com.uthmanIV.ise.user.stock.Stock;
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

    @Transactional
    public void buyStock(User user, BigDecimal numberOfShares, Stock stock){
        BigDecimal stockPrice = numberOfShares
                .multiply(stock.getCurrentPrice());
        if (!walletService.hasSufficientFunds(user, stockPrice)) {
            throw new InsufficientFundsException("Insufficient funds to complete the purchase.");
        }
        walletService.updateWalletBalance(user.getWallet(),stockPrice,"ADD");
        portfolioService.updateUserPortfolioValue(user,"BUY", stockPrice);
        portfolioService.addStockToPortfolio(user,stock);
        earningsService.addEarnings(user,numberOfShares,stock);//update influencer earning
        walletRepository.save(user.getWallet());
    }

    @Transactional
    public void sellStock(User user, BigDecimal numberOfShares, Stock stock){
        BigDecimal stockPrice = numberOfShares.multiply(stock.getCurrentPrice());
        Wallet wallet = user.getWallet();
        walletService.updateWalletBalance(wallet,stockPrice,"SUBTRACT");
        portfolioService.updateUserPortfolioValue(user,"SELL", stockPrice);
        portfolioService.removeStockFromPortfolio(user, stock);
        walletRepository.save(wallet);
    }
}
