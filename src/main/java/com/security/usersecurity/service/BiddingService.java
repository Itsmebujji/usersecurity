package com.security.usersecurity.service;

import com.security.usersecurity.model.BiddingModel;
import com.security.usersecurity.repository.BiddingModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BiddingService {
    @Autowired
    private BiddingModelRepo biddingModelRepo;
    @Autowired
    private UserService userService;
    //201,400
    public ResponseEntity<Object> postBidding(BiddingModel biddingModel){
        return null;
    }
    //200,400
    public ResponseEntity<Object> getBidding(double bidAmount){
        return null;
    }
    //200,400
    public ResponseEntity<Object> updateBidding(int id, BiddingModel biddingModel){
        return null;
    }

    public ResponseEntity<Object> deleteBidding(int id){
        return null;
    }
}
