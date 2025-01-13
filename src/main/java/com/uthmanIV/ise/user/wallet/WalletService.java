package com.uthmanIV.ise.user.wallet;

import com.uthmanIV.ise.user.User;
import com.uthmanIV.ise.user.transaction.TransactionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    @Transactional
    public BigDecimal getWalletBalance(User user){
        return user.getWallet().getWalletBalance();
    }

    public boolean hasSufficientFunds(User user, BigDecimal amount) {
        Wallet userWallet = user.getWallet();
        BigDecimal newBalance = userWallet.getWalletBalance().subtract(amount);
        // Check if newBalance is greater than or equal to 0
        return newBalance.compareTo(BigDecimal.ZERO) >= 0;
    }

    @Transactional
    public void updateWalletBalance(Wallet wallet,
                                    BigDecimal amount,
                                    TransactionType transactionType){
        if (transactionType.equals(TransactionType.DEPOSIT) ||transactionType.equals(TransactionType.SELL)){
            wallet.setWalletBalance(wallet
                    .getWalletBalance()
                    .add(amount));
        }
        else {
            wallet.setWalletBalance(wallet
                    .getWalletBalance()
                    .subtract(amount));
        }

        walletRepository.save(wallet);
    }

}
