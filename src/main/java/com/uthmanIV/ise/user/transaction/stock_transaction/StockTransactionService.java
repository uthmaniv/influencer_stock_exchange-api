package com.uthmanIV.ise.user.transaction.stock_transaction;

import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.stock.Stock;
import com.uthmanIV.ise.user.transaction.TransactionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockTransactionService {

    private StockTransactionMapper stockTransactionMapper;
    private final StockTransactionRepository stockTransactionRepository;

    @Transactional
    public void addTransaction(User user,
                               TransactionType transactionType, Stock stock,
                               BigDecimal numberOfShares,
                               BigDecimal unitPrice,
                               BigDecimal totalAmount
                                   ){
        StockTransaction newTransaction= new StockTransaction();
        newTransaction.setDate(LocalDateTime.now()); // might need refactor
        newTransaction.setUser(user);
        newTransaction.setTransactionType(transactionType);
        newTransaction.setStock(stock);
        newTransaction.setNumberOfShares(numberOfShares);
        newTransaction.setStockUnitPrice(unitPrice);
        newTransaction.setAmount(totalAmount);
        //should devise a way to verify and add transactionStatus
        stockTransactionRepository.save(newTransaction);

    }

    public List<StockTransactionDto> getStockTransactions(Long userId){
        List<StockTransaction> stockTransactions = stockTransactionRepository
                .findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("No transaction were found for this user"));
        return stockTransactionMapper.toDtoList(stockTransactions);
    }

}
