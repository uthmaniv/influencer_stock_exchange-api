package com.uthmanIV.ise.influencer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InfluencerRepository extends JpaRepository<Influencer,Long> {
//    Optional<Influencer> findByTier(String tier);
    Optional<Influencer> findByUserId(Long id);
    boolean existsByStockSymbol(String symbol);
}
