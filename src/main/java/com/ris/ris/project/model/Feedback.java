package com.ris.ris.project.model;

import javax.persistence.*;

@Entity
public class Feedback {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedbackID;

    @Enumerated(value = EnumType.STRING)//NOTE to self: Maybe change it to Ordinal?
    private FeedbackGrade grade;

    /*We want to have more than 255 characters available to the user when writing a description*/
    @Lob
    @Column(columnDefinition = "CLOB")
    private String description;

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;

    @OneToOne
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
