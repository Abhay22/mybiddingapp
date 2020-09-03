package com.bidding.service;

import com.bidding.domain.AuctionEntity;
import com.bidding.dto.AuctionResponseDTO;
import com.bidding.dto.BiddingRequest;
import com.bidding.repository.AuctionRepository;
import com.bidding.rules.StepRule;
import com.bidding.util.BidStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final AuctionRepository auctionRepository;
    private final ProcessBid processBid;
    private final StepRule stepRule;

    public AuctionService(AuctionRepository auctionRepository, ProcessBid processBid, StepRule stepRule) {
        this.auctionRepository = auctionRepository;
        this.processBid = processBid;
        this.stepRule = stepRule;
    }

    public List<AuctionResponseDTO> getAllAuctions(String status,Integer pageNo, Integer pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<AuctionEntity> auctionEntities = new ArrayList<>();
        auctionRepository.findAll(paging).forEach(auctionEntity -> {
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
