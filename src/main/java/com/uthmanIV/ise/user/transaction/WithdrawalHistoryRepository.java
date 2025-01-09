package com.uthmanIV.ise.user.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalHistoryRepository extends JpaRepository<WithdrawalHistory,Long> {

    List<WithdrawalHistory> findWithdrawalHistoryByUserId(Long id);
}
