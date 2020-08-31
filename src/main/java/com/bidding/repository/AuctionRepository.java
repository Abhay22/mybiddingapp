package com.bidding.repository;

import com.bidding.domain.AuctionEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Abhay Pandit
 */
public interface AuctionRepository extends CrudRepository<AuctionEntity,Long> {
}
