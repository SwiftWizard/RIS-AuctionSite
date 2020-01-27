package com.ris.ris.project.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionID;

    private String title;

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
    @JoinColumn(name="buyer_userID")
    private User buyer;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private double initialPrice;

    @OneToMany(mappedBy = "auction", targetEntity = Bid.class, fetch = FetchType.EAGER)
    @OrderBy("amount")
    private SortedSet<Bid> bidders = new TreeSet<>();

    @OneToOne
    private Feedback feedbackBuyerToSeller;

    @OneToOne
    private  Feedback feedbackSellerToBuyer;

    private boolean sellerLeftFeedback = false;

    private boolean buyerLeftFeedback = false;

    public Auction() {
    }

    public Long getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(Long auctionID) {
        this.auctionID = auctionID;
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

    public AuctionState getAuctionState() {
        return auctionState;
    }

    public void setAuctionState(AuctionState auctionState) {
        this.auctionState = auctionState;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public SortedSet<Bid> getBidders() {
        return bidders;
    }

    public void setBidders(SortedSet<Bid> bidders) {
        this.bidders = bidders;
    }

    public Auction addBid(Bid bid){
        this.bidders.add(bid);
        bid.setAuction(this);
        return this;
    }

    public void setInitialPrice(double initialPrice) {
        String initialPriceStr = String.format("%.2f", initialPrice);
        this.initialPrice = Double.valueOf(initialPriceStr);
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public boolean isSellerLeftFeedback() {
        return sellerLeftFeedback;
    }

    public void setSellerLeftFeedback(boolean sellerLeftFeedback) {
        this.sellerLeftFeedback = sellerLeftFeedback;
    }

    public boolean isBuyerLeftFeedback() {
        return buyerLeftFeedback;
    }

    public void setBuyerLeftFeedback(boolean buyerLeftFeedback) {
        this.buyerLeftFeedback = buyerLeftFeedback;
    }

}
