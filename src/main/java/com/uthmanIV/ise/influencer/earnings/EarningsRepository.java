package com.uthmanIV.ise.influencer.earnings;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EarningsRepository extends JpaRepository<Earnings,Long> {

    List<Earnings> findByUserId(Long id);
}
