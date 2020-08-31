package com.bidding.service;

import com.bidding.domain.AuctionEntity;
import com.bidding.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abhay Pandit
 */
@Service
public class AuctionService {
    @Autowired
    AuctionRepository auctionRepository;

    public List<AuctionEntity> getAllAuctions(String status){
        List<AuctionEntity> auctionEntities = new ArrayList<>();
        auctionRepository.findAll().forEach(auctionEntity -> {
            if(auctionEntity.getStatus().equalsIgnoreCase(status))
                auctionEntities.add(auctionEntity) ;
        });
        return auctionEntities;

    }
}
