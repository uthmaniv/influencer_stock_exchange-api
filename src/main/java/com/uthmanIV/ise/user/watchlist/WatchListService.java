package com.uthmanIV.ise.user.investor.watchlist;

import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.UserRepository;
import com.uthmanIV.ise.user.stock.Stock;
import com.uthmanIV.ise.user.stock.StockMapper;
import com.uthmanIV.ise.user.stock.StockResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchListService {

    private final WatchListRepository watchListRepository;
    private final StockMapper stockMapper;
    private final UserRepository userRepository;

    public List<StockResponseDto> getWatchList(Long id) {
        WatchList watchList = watchListRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Watchlist with ID " + id + " not found"));
        return stockMapper.toDtoList(watchList.getStocks());
    }

    public void addToWatchList(User user, Stock stock){
        user.getWatchList()
                .getStocks()
                .add(stock);
        userRepository.save(user);
    }

    public void removeFromWatchlist(User user, Stock stock){
        user.getWatchList()
                .getStocks()
                .remove(stock);
        userRepository.save(user);
    }

    public void clearWatchlist(User user){
        user.getWatchList()
                .getStocks()
                .clear();
        userRepository.save(user);
    }

}
