package com.uthmanIV.ise.user.investor.watchlist;

import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
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

    public List<StockResponseDto> investorWatchList(Long id) {
        WatchList watchList = watchListRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Watchlist with ID " + id + " not found"));
        return stockMapper.toDtoList(watchList.getStocks());
    }

}
