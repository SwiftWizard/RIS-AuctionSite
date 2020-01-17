package com.ris.ris.project.model;

import java.time.LocalDateTime;
import java.util.List;

public class Auction {
    private String title;
    private String description;
    private LocalDateTime dateTimeOfAuctionStart;
    private LocalDateTime dateTimeOfAuctionEnd;
    private Byte[] imageA;
    private Byte[] imageB;
    private Byte[] imageC;

    private User seller;
    private User buyer;
    private Categories category;
    private Bid currentMaxBidder;
    private List<Bid> bidders;

    public Auction() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTimeOfAuctionStart() {
        return dateTimeOfAuctionStart;
    }

    public void setDateTimeOfAuctionStart(LocalDateTime dateTimeOfAuctionStart) {
        this.dateTimeOfAuctionStart = dateTimeOfAuctionStart;
    }

    public LocalDateTime getDateTimeOfAuctionEnd() {
        return dateTimeOfAuctionEnd;
    }

    public void setDateTimeOfAuctionEnd(LocalDateTime dateTimeOfAuctionEnd) {
        this.dateTimeOfAuctionEnd = dateTimeOfAuctionEnd;
    }

    public Byte[] getImageA() {
        return imageA;
    }

    public void setImageA(Byte[] imageA) {
        this.imageA = imageA;
    }

    public Byte[] getImageB() {
        return imageB;
    }

    public void setImageB(Byte[] imageB) {
        this.imageB = imageB;
    }

    public Byte[] getImageC() {
        return imageC;
    }

    public void setImageC(Byte[] imageC) {
        this.imageC = imageC;
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

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Bid getCurrentMaxBidder() {
        return currentMaxBidder;
    }

    public void setCurrentMaxBidder(Bid currentMaxBidder) {
        this.currentMaxBidder = currentMaxBidder;
    }

    public List<Bid> getBidders() {
        return bidders;
    }

    public void setBidders(List<Bid> bidders) {
        this.bidders = bidders;
    }
}
