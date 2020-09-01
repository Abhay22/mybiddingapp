package com.bidding.repository;

import com.bidding.domain.BiddingData;
import org.springframework.data.repository.CrudRepository;

public interface BiddingRepo extends CrudRepository<BiddingData, Integer> {

}
