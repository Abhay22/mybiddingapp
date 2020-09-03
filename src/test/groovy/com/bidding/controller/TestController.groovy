package com.bidding.controller

import com.bidding.domain.AuctionEntity
import com.bidding.dto.AuctionResponseDTO
import com.bidding.dto.BiddingRequest
import com.bidding.repository.AuctionRepository
import com.bidding.security.component.TokenGeneration
import com.bidding.service.AuctionService
import com.bidding.util.BidStatus
import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification
import spock.lang.Unroll;
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

/*
 * @author Abhay Pandit
 */
class TestController extends Specification{

    def auctionService = mock(AuctionService.class)
    def tokenGeneration = mock(TokenGeneration.class)
    def controller = new Controller(auctionService,tokenGeneration)

    @Unroll
    def 'fetch all auctions'() {
        given:
        AuctionResponseDTO auctionResponseDTO=new AuctionResponseDTO()
        auctionResponseDTO.setItemCode("bid1000")
        auctionResponseDTO.setBasePrice(1000)
        auctionResponseDTO.setStepRate(0);
        auctionResponseDTO.setStatus("RUNNING")
        List<AuctionResponseDTO> allAuctions= new ArrayList<>()
        allAuctions.add(auctionResponseDTO)
        when(auctionService.getAllAuctions("RUNNING")).thenReturn(allAuctions)
        when:
        def resp = controller.getAcutions("RUNNING")
        then:
        resp.statusCodeValue == 200
    }

    @Unroll
    def 'fetch bearer token'() {
        given:

        when(tokenGeneration.getToken("test")).thenReturn("abhay")

        when:

        def resp = controller.getUserToken("test", "test")

        then:

        resp.token == "abhay"
    }

    @Unroll
    def 'place bid'() {
        given:

        BiddingRequest biddingRequest=new BiddingRequest();
        biddingRequest.setItemCode("123")
        biddingRequest.setBidAmount(1000)
        when(auctionService.placeBid(biddingRequest)).thenReturn(BidStatus.ACCEPTED)

        when:

        def resp = controller.placeBid(biddingRequest)

        then:

        resp.statusCodeValue == 200
    }


}
