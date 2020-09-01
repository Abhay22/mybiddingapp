package com.bidding.util;

import org.springframework.http.HttpStatus;
/**
 * @author Abhay Pandit
 */
public enum BidStatus {
    ACCEPTED("Bid is accepted", HttpStatus.OK),
    NOT_FOUND("Auction not found",HttpStatus.NOT_FOUND),
    REJECTED("Bid is rejected",HttpStatus.NOT_ACCEPTABLE),
    ERROR_PROCESSING("Error in processing Bid",HttpStatus.INTERNAL_SERVER_ERROR);

    private String reason;
    private HttpStatus code;

    BidStatus(String reason, HttpStatus code) {
        this.reason = reason;
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public HttpStatus getCode() {
        return code;
    }
}
