package com.uthmanIV.ise.stock;

import com.uthmanIV.ise.influencer.Influencer;
import com.uthmanIV.ise.influencer.InfluencerTier;
import com.uthmanIV.ise.portfolio.PortfolioService;
import com.uthmanIV.ise.wallet.WalletRepository;
import com.uthmanIV.ise.wallet.WalletService;
import lombok.RequiredArgsConstructor;

import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final WalletService walletService;
    private final PortfolioService portfolioService;
    private final StockRepository stockRepository;
    private final WalletRepository walletRepository;
    private final StockMapper stockMapper;

    @Named("stockSymbolFromInfluencer")
    public String getStockSymbol(Influencer influencer){
        return influencer.getStockSymbol();
    }

    @Named("stockSymbolFromStock")
    public String getStockSymbol(Stock stock){
        return getStockSymbol(stock.getInfluencer());
    }

    public List<StockResponseDto> getStocksByInfluencerTier(InfluencerTier tier){
        return stockMapper.toDtoList(stockRepository
                .findStocksByInfluencerTier(tier));
    }
    public List<StockResponseDto> getStocksByIndustry(String industry) {
        return stockMapper.toDtoList(stockRepository
                .findStocksByAudienceIndustry(industry));
    }

}
