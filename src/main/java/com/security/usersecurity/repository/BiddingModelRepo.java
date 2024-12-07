package com.security.usersecurity.repository;

import com.security.usersecurity.model.BiddingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiddingModelRepo extends JpaRepository<BiddingModel,Integer> {
}
