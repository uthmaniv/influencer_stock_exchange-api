package com.uthmanIV.ise.user.portfolio;

import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.investor.Investor;
import com.uthmanIV.ise.user.stock.Stock;
import com.uthmanIV.ise.user.stock.StockMapper;
import com.uthmanIV.ise.user.stock.StockRepository;
import com.uthmanIV.ise.user.stock.StockResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public List<StockResponseDto> getPortfolioStocks(Long userId){
        Portfolio portfolio = portfolioRepository
                .findById(userId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Portfolio Not Found For user with id: " + userId));
        return stockMapper
                .toDtoList(portfolio.getStocks());
    }

    @Transactional
    public void updateUserPortfolioValue(User user, String transactionType,BigDecimal stockValue){
        Portfolio portfolio = user.getPortfolio();

        if (transactionType.equals("BUY")){
            portfolio.setStockValue(portfolio
                    .getStockValue()
                    .add(stockValue));
        }
        else {
            portfolio.setStockValue(portfolio
                    .getStockValue()
                    .subtract(stockValue));
        }

        portfolioRepository.save(portfolio);
    }

    public void updateInfluencerEarningOnPurchase(Stock stock,BigDecimal stockPrice){
        stockRepository.save(stock);
    }

    @Transactional
    public void addStockToPortfolio(User user,Stock newStock){
        Portfolio portfolio = user.getPortfolio();

        portfolio.getStocks().add(newStock);

        portfolioRepository.save(portfolio);
    }

    @Transactional
    public void removeStockFromPortfolio(User user, Stock stock){
        Portfolio portfolio = user.getPortfolio();

        portfolio.getStocks().remove(stock);
        portfolioRepository.save(portfolio);
    }

}
