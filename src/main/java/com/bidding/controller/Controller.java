package com.bidding.controller;

import com.bidding.domain.AuctionEntity;
import com.bidding.dto.User;
import com.bidding.security.component.TokenGeneration;
import com.bidding.service.AuctionService;
import com.bidding.util.AuctionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Abhay Pandit
 */
@RestController
public class Controller {

    @Autowired
    AuctionService auctionService;

    @Autowired
    TokenGeneration tokenGeneration;

    @GetMapping("/test")
    public String test() {
        return "Test Successful!!";
    }

    @GetMapping("/auction")
    public ResponseEntity getActions(@RequestParam(value = "status", required = false) String status) {
        List<AuctionEntity> allAuctions = auctionService.getAllAuctions(
                (status == null ? AuctionStatus.RUNNING : status).toString());
        return new ResponseEntity(allAuctions, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public User getUserToken(@RequestParam("user") String username, @RequestParam("password") String pwd){
        String token = tokenGeneration.getToken(username);
        User user = new User();
        user.setUserName(username);
        user.setToken(token);
        return user;
    }
}
