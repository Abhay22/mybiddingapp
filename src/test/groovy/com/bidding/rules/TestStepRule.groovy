package com.bidding.rules

import com.bidding.domain.AuctionEntity
import com.bidding.dto.BiddingRequest
import spock.lang.Specification
import spock.lang.Unroll;

/*
 * @author Abhay Pandit
 */

class TestStepRule extends Specification {

    /*@Unroll
    def 'initial invalid bid'() {
        given:
        def biddingRule = new BidValidationRules()
        def bidRequest = BiddingRequest.builder().itemCode("ABC123").bidAmount(100).build()
        def aucEntity = AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(0).stepRate(100).status("RUNNING").build()

        when:
        def resp = biddingRule.apply(aucEntity, bidRequest)
        then:
        !resp
    }*/

    @Unroll
    def 'bid validation'() {
        given:
        BiddingRequest biddingRequest = new BiddingRequest();
        biddingRequest.setItemCode("123")
        biddingRequest.setBidAmount(bidAmount)
        def stepRuleImpl = new StepRuleImpl()
        def auctionEntity = new AuctionEntity();
        auctionEntity.setItemCode("100")
        auctionEntity.setStatus("RUNNING")
        auctionEntity.setStepRate(100)
        auctionEntity.setBasePrice(1000)
        auctionEntity.setBidPrice(dbBidPrice)
        auctionEntity.setItemid(123)

        when:
        def resp = stepRuleImpl.processStepRule(auctionEntity, biddingRequest)
        then:
        resp == expected
        where:
        label             | bidAmount | dbBidPrice | expected
        "initial valid "  | 1200      | 0          | true
        "initial invalid" | 10        | 0          | false
        "valid"           | 1300      | 1200       | true
        "invalid"         | 10        | 1200       | false
    }
    /* @Unroll
     def 'valid bid'() {
         given:
         def biddingRule = new BidValidationRules()
         def bidRequest = BiddingRequest.builder().itemCode("ABC123").bidAmount(1200).build()
         def aucEntity = AuctionEntity.builder().itemId(1001).itemCode("ABC123").basePrice(1000).bidPrice(1100).stepRate(100).status("RUNNING").build()

         when:
         def resp = biddingRule.apply(aucEntity, bidRequest)
         then:
         resp
     }
 */
}
