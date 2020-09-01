package com.bidding.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BIDDINGDATA")
public class BiddingData {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="itemid")
    private Long itemId;

    @Column(name="username")
    private String userName;

    @Column(name="bidprice")
    private int bidPrice;

    public BiddingData() {
    }
    public BiddingData(Long itemId, String userName, int bidPrice) {
        this.itemId = itemId;
        this.userName = userName;
        this.bidPrice = bidPrice;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }
}
