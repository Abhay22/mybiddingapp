package com.bidding.service;

/*
 * @author Abhay Pandit
 */
import com.bidding.domain.AuctionEntity
import com.bidding.dto.BiddingRequest
import com.bidding.repository.AuctionRepository
import com.bidding.repository.BiddingRepo
import spock.lang.Specification
import spock.lang.Unroll
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
class TestProcessBid extends Specification {
    def auctionRepository = mock(AuctionRepository.class)
    def bidRepo = mock(BiddingRepo.class)
    def processBid = new ProcessBid(bidRepo, auctionRepository)
    @Unroll
    def "place bid service  #label"() {
        given:
        BiddingRequest biddingRequest = new BiddingRequest();
        biddingRequest.setItemCode("100")
        biddingRequest.setBidAmount(1500)
        def entity = createEntity()
        when(auctionRepository.save(entity)).thenReturn(entity)
        when:
        def resp = processBid.updateAuctionDetails(entity, biddingRequest)
        then:
        resp.itemCode == "100"
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