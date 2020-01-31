package com.ris.ris.project.repository;

import com.ris.ris.project.model.Auction;
import com.ris.ris.project.model.Message;
import com.ris.ris.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByAuction(Auction auction);

    List<Message> findAllBySenderAndReceiverAndAuctionOrderByDateTimeOfMessageSent(User sender, User receiver, Auction auction);

    List<Message> findAllByReceiverAndAuction(User receiver, Auction auction);

    List<Message> findAllByUsersContainingAndAuction(User user, Auction auction);

    List<Message> findAllByUsersContaining(User user);

    List<Message> findAllByReceiver(User receiver);

    List<Message> findAllBySender(User sender);
}
