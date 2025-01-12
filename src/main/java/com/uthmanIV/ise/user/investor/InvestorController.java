package com.uthmanIV.ise.user.investor;

import com.uthmanIV.ise.api_response.ApiResponse;
import com.uthmanIV.ise.exceptions.InsufficientFundsException;
import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.portfolio.PortfolioService;
import com.uthmanIV.ise.user.stock.StockResponseDto;
import com.uthmanIV.ise.user.stock.stock_transaction.StockTransactionDto;
import com.uthmanIV.ise.user.stock.stock_transaction.StockTransactionService;
import com.uthmanIV.ise.user.watchlist.WatchListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/investor")
@RequiredArgsConstructor
public class InvestorController {

    private final WatchListService watchListService;
    private final PortfolioService portfolioService;
    private final StockTransactionService stockTransactionService;

    @GetMapping("{userId}/stocks")
    public ResponseEntity<ApiResponse> getStocks(@PathVariable Long userId){
        try{
            List<StockResponseDto> stocks = portfolioService.getPortfolioStocks(userId);
            return ResponseEntity.ok(new ApiResponse("Portfolio Fetched successfully",stocks));
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("{userId}/watchlist")
    public ResponseEntity<ApiResponse> getWatchList(@PathVariable Long userId){
        try{
            List<StockResponseDto> watchList = watchListService.getWatchList(userId);
            return ResponseEntity.ok(new ApiResponse("Success",watchList));
        }
        catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("{userId}/stockTransactions")
    public ResponseEntity<ApiResponse> getStockTransactions(@PathVariable Long userId){
        try{
            List<StockTransactionDto> transactions = stockTransactionService.getStockTransactions(userId);
            return ResponseEntity.ok(new ApiResponse("Success", transactions));
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("{userId}/stock")
    public ResponseEntity<?> buyStock(@PathVariable Long userId,
                                      @RequestParam Long stockId,
                                      @RequestParam @Valid BigDecimal numberOfShares){
        try{
            portfolioService.buyStock(userId,stockId,numberOfShares);
            return ResponseEntity.noContent().build();
        }
        catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
        catch (InsufficientFundsException e){
            return  ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("{userId}/stock")
    public ResponseEntity<?> sellStock(@PathVariable Long userId,
                                       @RequestParam @Valid BigDecimal numberOfShares,
                                       @RequestParam Long stockId){
        try{
            portfolioService.sellStock(userId,numberOfShares,stockId);
            return ResponseEntity.noContent().build();
        }
        catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("{userId}/watchlist")
    public ResponseEntity<ApiResponse> addToWatchList(@PathVariable Long userId,
                                                      @RequestParam Long stockId){
        try{
            watchListService.addToWatchList(userId,stockId);
            return ResponseEntity
                    .status(CREATED)
                    .body(new ApiResponse("Successfully added to watchlist", null));

        }
        catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("{id}/watchlist")
    public ResponseEntity<?> removeFromWatchlist(@PathVariable Long id,
                                                 @RequestParam Long stockId) {
        try {
            watchListService.removeFromWatchlist(id, stockId);
            return ResponseEntity.noContent().build(); // Return 204 No Content on success
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

}
