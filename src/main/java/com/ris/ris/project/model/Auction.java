package com.ris.ris.project.model;

import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionID;

    private String title;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String description;

    private LocalDateTime dateTimeOfAuctionStart;
    private LocalDateTime dateTimeOfAuctionEnd;

    @Lob
    @Column(columnDefinition = "BLOB")
    private Byte[] imageA;

    @Lob
    @Column(columnDefinition = "BLOB")
    private Byte[] imageB;

    @Lob
    @Column(columnDefinition = "BLOB")
    private Byte[] imageC;

    @Enumerated(value = EnumType.STRING)
    private AuctionState auctionState;

    @Enumerated(value = EnumType.STRING)
    private Categories category;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User seller;

    @ManyToOne
    private User buyer;

    private float initialPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auction", targetEntity = Bid.class)
    @OrderBy("amount")
    private SortedSet<Bid> bidders;

    @OneToOne
    private Feedback feedbackForAuction;

    public Auction() {
    }

    public Long getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(Long auctionID) {
        this.auctionID = auctionID;
    }

    public AuctionState getAuctionState() {
        return auctionState;
    }

    public void setAuctionState(AuctionState auctionState) {
        this.auctionState = auctionState;
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

    public SortedSet<Bid> getBidders() {
        return bidders;
    }

    public void setBidders(SortedSet<Bid> bidders) {
        this.bidders = bidders;
    }

    public float getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(float initalPrice) {
        this.initialPrice = initalPrice;
    }

    public Feedback getFeedbackForAuction() {
        return feedbackForAuction;
    }

    public void setFeedbackForAuction(Feedback feedbackForAuction) {
        this.feedbackForAuction = feedbackForAuction;
    }
}
