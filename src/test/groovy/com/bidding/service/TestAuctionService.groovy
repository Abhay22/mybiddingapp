package com.bidding.service

import com.bidding.domain.AuctionEntity
import com.bidding.dto.BiddingRequest
import com.bidding.repository.AuctionRepository
import com.bidding.rules.StepRule
import com.bidding.util.BidStatus
import spock.lang.Specification
import spock.lang.Unroll

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

/*
 * @author Abhay Pandit
 */
class TestAuctionService extends Specification {
    def auctionRepository = mock(AuctionRepository.class);
    def processBid = mock(ProcessBid.class)
    def stepRule = mock(StepRule.class)
    def auctionService = new AuctionService(auctionRepository, processBid, stepRule)
    @Unroll
    def "place bid service  #label"() {
        given:
        BiddingRequest biddingRequest = new BiddingRequest();
        biddingRequest.setItemCode("100")
        biddingRequest.setBidAmount(1500)
        when(auctionRepository.findByItemCode("100")).thenReturn(entity)
        when(stepRule.processStepRule(entity, biddingRequest)).thenReturn(bidrule)
        when:
        def resp = auctionService.placeBid(biddingRequest)
        then:
        resp.getReason() == expected
        where:
        label       | bidrule | entity         | expected
        "ACCEPTED"  | true    | createEntity() | BidStatus.ACCEPTED.getReason()
        "REJECTED"  | false   | createEntity() | BidStatus.REJECTED.getReason()
        "NOT FOUND" | true    | null           | BidStatus.NOT_FOUND.getReason()
    }
    @Unroll
    def "exception place bid service  #label"() {
        given:
        BiddingRequest biddingRequest = new BiddingRequest();
        biddingRequest.setItemCode("100")
        biddingRequest.setBidAmount(1500)
        when(auctionRepository.findByItemCode("100")).thenReturn(entity)
        when(stepRule.processStepRule(entity, biddingRequest)).thenReturn(bidrule)
        when(processBid.updateAuctionDetails(entity, biddingRequest)).thenThrow(exception)
        when:
        def resp = auctionService.placeBid(biddingRequest)
        then:
        resp.getReason() == expected
        where:
        label              | bidrule | entity         | exception                  | expected
        "RuntimeException" | true    | createEntity() | new NullPointerException() | BidStatus.REJECTED.getReason()
    }
    def createEntity() {
        def auctionEntity = new AuctionEntity();
        auctionEntity.setItemCode("100")
        auctionEntity.setStatus("RUNNING")
        auctionEntity.setStepRate(100)
        auctionEntity.setBasePrice(1000)
        auctionEntity.setBidPrice(1200)
        auctionEntity.setItemid(123)
        return auctionEntity
    }
}