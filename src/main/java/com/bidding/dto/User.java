package com.bidding.dto;

/**
 * @author Abhay Pandit
 */
public class User {
    String userName;
    String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
