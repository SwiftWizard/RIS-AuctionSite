package com.ris.ris.project.model;

public class Feedback {
    private FeedbackGrade grade;
    private String description;
    private User seller;
    private User buyer;
    private Auction auction;

    public Feedback() {
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
