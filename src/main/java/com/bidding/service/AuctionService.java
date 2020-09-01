package com.bidding.service;

import com.bidding.domain.AuctionEntity;
import com.bidding.dto.AuctionResponseDTO;
import com.bidding.dto.BiddingRequest;
import com.bidding.repository.AuctionRepository;
import com.bidding.rules.StepRule;
import com.bidding.util.BidStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 * @author Abhay Pandit
 */
@Service
public class AuctionService {
    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    ProcessBid processBid;

    @Autowired
    StepRule stepRule;

    public List<AuctionResponseDTO> getAllAuctions(String status){
        List<AuctionEntity> auctionEntities = new ArrayList<>();
        auctionRepository.findAll().forEach(auctionEntity -> {
            if(auctionEntity.getStatus().equalsIgnoreCase(status))
                auctionEntities.add(auctionEntity) ;
        });
        return Arrays.asList(
                new ModelMapper()
                        .map(auctionEntities, AuctionResponseDTO[].class)
        );
    }

    public BidStatus placeBid(BiddingRequest biddingRequest){
        AuctionEntity auctionEntity = auctionRepository.findByItemCode(biddingRequest.getItemCode());
        if (auctionEntity==null){
            return BidStatus.NOT_FOUND;
        }

        if(!stepRule.processStepRule(auctionEntity,biddingRequest)){
            return BidStatus.REJECTED;
        }

        try {
            processBid.processUserBid(auctionEntity, biddingRequest);
            processBid.updateAuctionDetails(auctionEntity, biddingRequest);
            return BidStatus.ACCEPTED;
        }
        catch ( RuntimeException runtimeException){
            return BidStatus.REJECTED;
        }
        catch (Exception exception){
            return BidStatus.ERROR_PROCESSING;
        }
    }

}
