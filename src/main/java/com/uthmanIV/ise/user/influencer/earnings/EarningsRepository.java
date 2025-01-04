package com.uthmanIV.ise.user.influencer.earnings;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EarningsRepository extends JpaRepository<Earnings,Long> {

    List<Earnings> findByUserId(Long id);
}
