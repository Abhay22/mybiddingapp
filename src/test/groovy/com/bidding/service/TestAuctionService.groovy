package com.bidding.service

import com.bidding.controller.Controller
import com.bidding.domain.AuctionEntity
import com.bidding.dto.BiddingRequest
import com.bidding.repository.AuctionRepository
import com.bidding.rules.StepRule
import com.bidding.security.component.TokenGeneration
import spock.lang.Specification
import spock.lang.Unroll

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when;

/*
 * @author Abhay Pandit
 */
class TestAuctionService extends Specification{

    def auctionRepository= mock(AuctionRepository.class) ;
    def processBid = mock(ProcessBid.class)
    def stepRule = mock(StepRule.class)
    def auctionService = new AuctionService(auctionRepository, processBid, stepRule)

    @Unroll
    def 'place bid service'() {

        BiddingRequest biddingRequest = new BiddingRequest();
        biddingRequest.setItemCode("100")
        biddingRequest.setBidAmount(1500)

        def auctionEntity = new AuctionEntity();
        auctionEntity.setItemCode("100")
        auctionEntity.setStatus("RUNNING")
        auctionEntity.setStepRate(100)
        auctionEntity.setBasePrice(1000)
        auctionEntity.setBidPrice(1200)
        auctionEntity.setItemid(123)

        when(auctionRepository.findByItemCode(biddingRequest.getItemCode())).thenReturn(auctionEntity)

        when:
        def resp= auctionService.placeBid(biddingRequest)

        then:
        resp




    }

}
