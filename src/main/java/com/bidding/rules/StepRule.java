package com.bidding.rules;

import com.bidding.domain.AuctionEntity;
import com.bidding.dto.BiddingRequest;

public interface StepRule {
    public boolean processStepRule(AuctionEntity auctionEntity, BiddingRequest biddingRequest);
}
