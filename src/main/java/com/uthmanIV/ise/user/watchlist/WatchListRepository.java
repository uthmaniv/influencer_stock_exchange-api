package com.uthmanIV.ise.user.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WatchListRepository extends JpaRepository<WatchList,Long> {

    Optional<WatchList> findByUserId(Long userId);

}
