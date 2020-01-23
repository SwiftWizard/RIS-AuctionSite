package com.ris.ris.project.repository;

import com.ris.ris.project.model.Auction;
import com.ris.ris.project.model.Feedback;
import com.ris.ris.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findAllByAuction(Auction auction);

    List<Feedback> findAllBySeller(User seller);

    List<Feedback> findAllByBuyer(User buyer);

}
