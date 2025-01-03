package com.uthmanIV.ise.user.stock;

import com.uthmanIV.ise.exceptions.InsufficientFundsException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.influencer.Influencer;
import com.uthmanIV.ise.user.portfolio.PortfolioService;
import com.uthmanIV.ise.user.wallet.Wallet;
import com.uthmanIV.ise.user.wallet.WalletRepository;
import com.uthmanIV.ise.user.wallet.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class StockService {

    private final WalletService walletService;
    private final PortfolioService portfolioService;
    private final WalletRepository walletRepository;

    @Named("stockSymbolFromInfluencer")
    public String getStockSymbol(Influencer influencer){
        return influencer.getStockSymbol();
    }

    @Named("stockSymbolFromStock")
    public String getStockSymbol(Stock stock){
        return getStockSymbol(stock.getInfluencer());
    }

}
