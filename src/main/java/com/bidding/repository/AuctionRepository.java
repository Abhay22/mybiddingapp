package com.bidding.repository;

import com.bidding.domain.AuctionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Abhay Pandit
 */
public interface AuctionRepository extends PagingAndSortingRepository<AuctionEntity,Long> {

    @Query(value = "select * from auction a where a.itemcode= ?1",nativeQuery = true)
    AuctionEntity findByItemCode(String itemCode);
}
