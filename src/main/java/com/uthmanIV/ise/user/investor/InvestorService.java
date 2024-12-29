package com.uthmanIV.ise.user.investor;

import com.uthmanIV.ise.user.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestorService {

    private final StockRepository stockRepository;

    private final InvestorRepository investorRepository;


}
