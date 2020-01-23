package com.ris.ris.project.model;

import javax.persistence.*;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedbackID;

    @Enumerated(value = EnumType.STRING)
    private FeedbackGrade grade;

    /*We want to have more than 255 characters available to the user when writing a description*/

    private String description;

    @ManyToOne
    @JoinColumn(name = "seller_userID")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_userID")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "auction_auctionID")
    private Auction auction;

    public Feedback() {
    }

    public long getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(long feedbackID) {
        this.feedbackID = feedbackID;
    }


    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public FeedbackGrade getGrade() {
        return grade;
    }

    public void setGrade(FeedbackGrade grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
