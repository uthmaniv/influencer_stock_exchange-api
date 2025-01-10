package com.uthmanIV.ise.user.portfolio;

import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.UserRepository;
import com.uthmanIV.ise.user.stock.Stock;
import com.uthmanIV.ise.user.stock.StockMapper;
import com.uthmanIV.ise.user.stock.StockRepository;
import com.uthmanIV.ise.user.stock.StockResponseDto;
import com.uthmanIV.ise.user.wallet.WalletRepository;
import com.uthmanIV.ise.user.wallet.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final WalletService walletService;
    private final WalletRepository walletRepository;
    private final StockMapper stockMapper;


    public List<StockResponseDto> getPortfolioStocks(Long userId) {
        Portfolio portfolio = portfolioRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Portfolio not found for user with id: " + userId));

        // Extract stocks from the portfolio stocks and map them to StockResponseDto
        Set<Stock> stocks = portfolio.getPortfolioStocks()
                .stream()
                .map(PortfolioStock::getStock)  // Get the stock from each PortfolioStock
                .collect(Collectors.toSet());   // Collect into a set of stocks

        // Return the DTO list after mapping
        return stockMapper.toDtoList(stocks);
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
    public void buyStock(Long userId, Long stockId, BigDecimal numberOfShares) {
        // Retrieve the portfolio for the given user
        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found for user with id: " + userId));

        // Retrieve the stock to buy
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock with id: " + stockId + " not found"));
        BigDecimal stockTotalPrice = stock.getCurrentPrice().multiply(numberOfShares);

        // Find the PortfolioStock corresponding to the stock (if it already exists in the portfolio)
        PortfolioStock portfolioStock = portfolio.getPortfolioStocks().stream()
                .filter(ps -> ps.getStock().getId().equals(stockId))
                .findFirst()
                .orElse(null);

        // If PortfolioStock exists, update the number of shares and total price
        if (portfolioStock != null) {
            portfolioStock.setShares(portfolioStock.getShares().add(numberOfShares));
            portfolioStock.setTotalPrice(portfolioStock
                    .getTotalPrice()
                    .add(stockTotalPrice));
        } else {
            // If PortfolioStock doesn't exist, create a new one and set its values
            portfolioStock = new PortfolioStock();
            portfolioStock.setStock(stock);
            portfolioStock.setPortfolio(portfolio);
            portfolioStock.setShares(numberOfShares);
            portfolioStock.setTotalPrice(stock.getCurrentPrice().multiply(numberOfShares));

            // Add the new PortfolioStock to the portfolio's collection
            portfolio.getPortfolioStocks().add(portfolioStock);
        }

        // call updateUserPortfolioValue() with BUY
        // Save the updated portfolio
        portfolioRepository.save(portfolio);
    }

    @Transactional
    public void sellStock(Long userId, BigDecimal numberOfShares, Long stockId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found"));
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock with id: " + stockId + " not found"));

        Portfolio portfolio = user.getPortfolio();
        PortfolioStock portfolioStock = portfolio
                .getPortfolioStocks().stream()
                .filter(ps -> ps.getStock().getId().equals(stockId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found in portfolio"));

        if (portfolioStock.getShares().compareTo(numberOfShares) < 0) {
            throw new IllegalArgumentException("Insufficient shares to sell.");
        }

        BigDecimal stockPrice = numberOfShares.multiply(stock.getCurrentPrice());
        portfolioStock.setShares(portfolioStock.getShares().subtract(numberOfShares));
        portfolioStock.setTotalPrice(portfolioStock.getTotalPrice().subtract(stockPrice));

        if (portfolioStock.getShares().compareTo(BigDecimal.ZERO) == 0) {
            portfolio.getPortfolioStocks().remove(portfolioStock); // Remove stock if no shares left
        }

        walletService.updateWalletBalance(user.getWallet(), stockPrice, "ADD");
        // call updateUserPortfolioValue() with SELL
        walletRepository.save(user.getWallet());
    }


}
