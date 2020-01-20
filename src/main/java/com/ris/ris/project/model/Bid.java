package com.ris.ris.project.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
public class Bid implements Comparable<Bid>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auctionID")
    private Auction auction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private User bidder;

    @Column(nullable = false)
    private float amount;

    private LocalDateTime bidDateTime;

    public Bid() {
    }

    @Override
    public int compareTo(Bid other) {
        return Double.compare(this.amount, other.getAmount());
    }

    public Long getBidID() {
        return bidID;
    }

    public void setBidID(Long bidID) {
        this.bidID = bidID;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getBidDateTime() {
        return bidDateTime;
    }

    public void setBidDateTime(LocalDateTime bidDateTime) {
        this.bidDateTime = bidDateTime;
    }
}
