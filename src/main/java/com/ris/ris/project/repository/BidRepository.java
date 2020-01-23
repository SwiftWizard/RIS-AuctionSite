package com.ris.ris.project.repository;

import com.ris.ris.project.model.Bid;
import com.ris.ris.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    public List<Bid> findAllByBidder(User bidder);
}
