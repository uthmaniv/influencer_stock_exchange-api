package com.uthmanIV.ise.user.watchlist;

import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.UserRepository;
import com.uthmanIV.ise.user.stock.Stock;
import com.uthmanIV.ise.user.stock.StockMapper;
import com.uthmanIV.ise.user.stock.StockRepository;
import com.uthmanIV.ise.user.stock.StockResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchListService {

    private final WatchListRepository watchListRepository;
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final UserRepository userRepository;

    public List<StockResponseDto> getWatchList(Long userId) {
        WatchList watchList = watchListRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Watchlist not found for user with ID: " + userId));
        return stockMapper.toDtoList(watchList.getStocks());
    }


    @Transactional
    public void addToWatchList(Long userId, Long stockId) {
        WatchList watchList = watchListRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("WatchList not found for user ID: " + userId));

        Stock stock = stockRepository
                .findById(stockId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Stock not found with ID: " + stockId));

        // Add stock and save
        watchList.getStocks().add(stock);
        watchListRepository.save(watchList);
    }


    public void removeFromWatchlist(Long userId, Long stockId) {
        WatchList watchList = watchListRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("WatchList not found for user ID: " + userId));

        Stock stock = stockRepository
                .findById(stockId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Stock not found with ID: " + stockId));

        // Remove stock and save
        watchList.getStocks().remove(stock);
        watchListRepository.save(watchList);
    }

    public void clearWatchlist(User user){
        user.getWatchList()
                .getStocks()
                .clear();
        userRepository.save(user);
    }

}
