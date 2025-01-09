package com.uthmanIV.ise.user.transaction;

import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final WalletService walletService;
    private final TransactionRepository transactionRepository;
    private final WithdrawalHistoryRepository withdrawalHistoryRepository;
    private final TransactionMapper transactionMapper;

    public void addTransaction(User user,
                               BigDecimal amount,
                               TransactionType transactionType){
        Transaction newTransaction = new Transaction();
        newTransaction.setTransactionType(transactionType);
        newTransaction.setTransactionType(transactionType);
        newTransaction.setDate(LocalDateTime.now());
        newTransaction.setAmount(amount);
        newTransaction.setWallet(user.getWallet());
        newTransaction.setDescription("Demo Method");

        transactionRepository.save(newTransaction);

    }

    public List<TransactionDto> getTransactions(User user){
        return transactionMapper
                .toDtoList(transactionRepository
                        .findTransactionsByUserId(user.getId()));
    }

    public List<WithdrawalHistoryDto> getWithdrawals(User user){
        return transactionMapper
                .withdrawalsDto(withdrawalHistoryRepository
                        .findWithdrawalHistoryByUserId(user.getId()));
    }
}
