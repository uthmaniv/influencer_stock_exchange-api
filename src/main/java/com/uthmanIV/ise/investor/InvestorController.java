package com.uthmanIV.ise.investor;

import com.uthmanIV.ise.api_response.ApiSuccess;
import com.uthmanIV.ise.portfolio.PortfolioService;
import com.uthmanIV.ise.stock.StockResponseDto;
import com.uthmanIV.ise.transaction.stock_transaction.StockTransactionDto;
import com.uthmanIV.ise.transaction.stock_transaction.StockTransactionService;
import com.uthmanIV.ise.watchlist.WatchListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/investor")
@RequiredArgsConstructor
public class InvestorController {

    private final WatchListService watchListService;
    private final PortfolioService portfolioService;
    private final StockTransactionService stockTransactionService;

    @GetMapping("{userId}/stocks")
    public ResponseEntity<ApiSuccess> getStocks(@PathVariable Long userId) {
        List<StockResponseDto> stocks = portfolioService.getPortfolioStocks(userId);
        return ResponseEntity.ok(new ApiSuccess("Portfolio fetched successfully", stocks));
    }

    @GetMapping("{userId}/watchlist")
    public ResponseEntity<ApiSuccess> getWatchList(@PathVariable Long userId) {
        List<StockResponseDto> watchList = watchListService.getWatchList(userId);
        return ResponseEntity.ok(new ApiSuccess("Success", watchList));
    }

    @GetMapping("{userId}/stockTransactions")
    public ResponseEntity<ApiSuccess> getStockTransactions(@PathVariable Long userId) {
        List<StockTransactionDto> transactions = stockTransactionService.getStockTransactions(userId);
        return ResponseEntity.ok(new ApiSuccess("Success", transactions));
    }

    @PostMapping("{userId}/stock")
    public ResponseEntity<Void> buyStock(@PathVariable Long userId,
                                         @RequestParam Long stockId,
                                         @RequestParam @Valid BigDecimal numberOfShares) {
        portfolioService.buyStock(userId, stockId, numberOfShares);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{userId}/stock")
    public ResponseEntity<Void> sellStock(@PathVariable Long userId,
                                          @RequestParam @Valid BigDecimal numberOfShares,
                                          @RequestParam Long stockId) {
        portfolioService.sellStock(userId, numberOfShares, stockId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{userId}/watchlist")
    public ResponseEntity<ApiSuccess> addToWatchList(@PathVariable Long userId,
                                                     @RequestParam Long stockId) {
        watchListService.addToWatchList(userId, stockId);
        return ResponseEntity
                .status(CREATED)
                .body(new ApiSuccess("Successfully added to watchlist", null));
    }

    @DeleteMapping("{id}/watchlist")
    public ResponseEntity<Void> removeFromWatchlist(@PathVariable Long id,
                                                    @RequestParam Long stockId) {
        watchListService.removeFromWatchlist(id, stockId);
        return ResponseEntity.noContent().build();
    }
}
