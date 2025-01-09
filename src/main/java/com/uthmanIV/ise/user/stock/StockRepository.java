package com.uthmanIV.ise.user.stock;

import com.uthmanIV.ise.user.influencer.InfluencerTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface StockRepository extends JpaRepository<Stock,Long> {

    @Query("SELECT s FROM Stock s WHERE s.influencer.influencerTier = :tier")
    Set<Stock> findStocksByInfluencerTier(@Param("tier") InfluencerTier tier);

    @Query("SELECT DISTINCT s FROM Stock s " +
            "JOIN s.influencer i " +
            "JOIN i.audienceDetails a " +
            "JOIN a.industries ind " +
            "WHERE ind = :industry")
    Set<Stock> findStocksByAudienceIndustry(@Param("industry") String industry);

}
