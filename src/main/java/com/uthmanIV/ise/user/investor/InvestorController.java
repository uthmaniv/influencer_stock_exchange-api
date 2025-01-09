package com.uthmanIV.ise.user.investor;

import com.uthmanIV.ise.api_response.ApiResponse;
import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.portfolio.PortfolioService;
import com.uthmanIV.ise.user.stock.StockResponseDto;
import com.uthmanIV.ise.user.stock.StockService;
import com.uthmanIV.ise.user.transaction.Transaction;
import com.uthmanIV.ise.user.watchlist.WatchList;
import com.uthmanIV.ise.user.watchlist.WatchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/investor")
@RequiredArgsConstructor
public class InvestorController {

    private final InvestorService investorService;
    private final WatchListService watchListService;
    private final StockService stockService;
    private final PortfolioService portfolioService;

    @GetMapping("{id}/stocks")
    public ResponseEntity<ApiResponse> getStocks(@PathVariable Long id){
        try{
            List<StockResponseDto> stocks = portfolioService.getPortfolioStocks(id);
            return ResponseEntity.ok(new ApiResponse("Portfolio Fetched successfully",stocks));
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("{id}/watchlist")
    public ResponseEntity<ApiResponse> getWatchList(@PathVariable Long id){
        try{
            List<StockResponseDto> watchList = watchListService.getWatchList(id);
            return ResponseEntity.ok(new ApiResponse("Success",watchList));
        }
        catch (ResourceNotFoundException e){
            return  ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("{id}/watchlist")
    public ResponseEntity<ApiResponse> addToWatchList(@PathVariable Long id,
                                                      @RequestParam Long stockId){
        try{
            watchListService.addToWatchList(id,stockId);
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

//    @GetMapping
//    public ResponseEntity<List<Transaction>> getTransactions(){
//        return ResponseEntity.ok(null);
//    }
//
//    @GetMapping
//    public ResponseEntity<WatchList> getWatchList(){
//        return ResponseEntity.ok(null);
//    }


}
