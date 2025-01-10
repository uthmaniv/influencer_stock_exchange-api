package com.uthmanIV.ise.user.stock.stock_transaction;

import com.uthmanIV.ise.exceptions.InsufficientFundsException;
import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.UserRepository;
import com.uthmanIV.ise.user.influencer.earnings.EarningsService;
import com.uthmanIV.ise.user.portfolio.Portfolio;
import com.uthmanIV.ise.user.portfolio.PortfolioRepository;
import com.uthmanIV.ise.user.portfolio.PortfolioService;
import com.uthmanIV.ise.user.portfolio.PortfolioStock;
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
    private final PortfolioRepository portfolioRepository;

    @Transactional
    public void addTransactionData(){
        //This should add transaction data
        //on sale or purchase of a stock
    }

}
