package com.uthmanIV.ise.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WatchListRepository extends JpaRepository<WatchList,Long> {

  Optional<WatchList> findByUserId(Long userId);

}
