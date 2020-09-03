package com.bidding.rules;

import com.bidding.domain.AuctionEntity;
import com.bidding.dto.BiddingRequest;
import org.springframework.stereotype.Component;

/**
 * @author Abhay Pandit
 */
@Component
public class StepRuleImpl implements StepRule{
    @Override
    public boolean processStepRule(AuctionEntity auctionEntity, BiddingRequest biddingRequest) {

        if(auctionEntity.getBidPrice() == 0){
            return biddingRequest.getBidAmount() >= auctionEntity.getBasePrice()+auctionEntity.getStepRate();
        }
        return biddingRequest.getBidAmount() >= auctionEntity.getBidPrice()+auctionEntity.getStepRate();

    }
}
