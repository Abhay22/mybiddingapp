package com.bidding.service;

import com.bidding.domain.AuctionEntity;
import com.bidding.domain.BiddingData;
import com.bidding.dto.BiddingRequest;
import com.bidding.repository.AuctionRepository;
import com.bidding.repository.BiddingRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Abhay Pandit
 */
@Service
public class ProcessBid {

    private final BiddingRepo biddingRepo;

    private final AuctionRepository auctionRepository;

    public ProcessBid(BiddingRepo biddingRepo, AuctionRepository auctionRepository) {
        this.biddingRepo = biddingRepo;
        this.auctionRepository = auctionRepository;
    }

    public void processUserBid(AuctionEntity auctionEntity, BiddingRequest biddingRequest){

        biddingRepo.save(new BiddingData(auctionEntity.getItemid(), (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal(),biddingRequest.getBidAmount()));

    }

    public AuctionEntity updateAuctionDetails(AuctionEntity auctionEntity, BiddingRequest biddingRequest) {
        auctionEntity.setBidPrice(biddingRequest.getBidAmount());
        auctionRepository.save(auctionEntity);
        return auctionEntity;
    }
}
