package com.uthmanIV.ise.user.transaction;

import com.uthmanIV.ise.exceptions.InsufficientFundsException;
import com.uthmanIV.ise.exceptions.ResourceNotFoundException;
import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.UserRepository;
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
    private final UserRepository userRepository;

    public void addTransaction(User user,
                               BigDecimal amount,
                               TransactionType transactionType){
        Transaction newTransaction = new Transaction();
        newTransaction.setUser(user);
        newTransaction.setTransactionType(transactionType);
        newTransaction.setAmount(amount);
        newTransaction.setDate(LocalDateTime.now());
        newTransaction.setDescription("Demo Method");

        transactionRepository.save(newTransaction);
    }

    public List<TransactionDto> getTransactions(Long userId){
        List<Transaction> transactions = transactionRepository.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("No transaction made for this user"));
        return transactionMapper
                .toDtoList(transactions);
    }

    public List<WithdrawalHistoryDto> getWithdrawals(Long userId){
        List<WithdrawalHistory> withdrawals = withdrawalHistoryRepository.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("No withdrawals made by this user"));
        return transactionMapper
                .withdrawalsDto(withdrawals);
    }

    public void deposit(Long userId, BigDecimal amount){
        User user = userRepository.findById(userId)
                        .orElseThrow(()-> new ResourceNotFoundException("User not found with id: )" + userId));
        walletService.updateWalletBalance(user.getWallet(),amount,TransactionType.DEPOSIT);
        addTransaction(user,amount,TransactionType.DEPOSIT);

    }

    public void withdraw(Long userId, BigDecimal amount){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id : " + userId));
        //check for sufficient funds in wallet
        if(!walletService.hasSufficientFunds(user,amount)){
            throw new InsufficientFundsException("Insufficient funds to withdraw");
        }
        walletService.updateWalletBalance(user.getWallet(),amount,TransactionType.WITHDRAWAL);
        addTransaction(user,amount,TransactionType.WITHDRAWAL);
    }
}
