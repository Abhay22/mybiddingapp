package com.bidding.service;

import com.bidding.domain.AuctionEntity;
import com.bidding.domain.BiddingData;
import com.bidding.dto.BiddingRequest;
import com.bidding.dto.User;
import com.bidding.repository.AuctionRepository;
import com.bidding.repository.BiddingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Abhay Pandit
 */
@Service
public class ProcessBid {

    @Autowired
    BiddingRepo biddingRepo;

    @Autowired
    AuctionRepository auctionRepository;

    public void processUserBid(AuctionEntity auctionEntity, BiddingRequest biddingRequest){

        biddingRepo.save(new BiddingData(auctionEntity.getItemid(), (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal(),biddingRequest.getBidAmount()));

    }

    public AuctionEntity updateAuctionDetails(AuctionEntity auctionEntity, BiddingRequest biddingRequest) {
        auctionEntity.setBidPrice(biddingRequest.getBidAmount());
        auctionRepository.save(auctionEntity);
        return auctionEntity;
    }
}
