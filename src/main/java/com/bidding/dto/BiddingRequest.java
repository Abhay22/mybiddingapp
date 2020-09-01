package com.bidding.dto;

/**
 * @author Abhay Pandit
 */
public class BiddingRequest {

    private String itemCode;
    private int bidAmount;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }
}
