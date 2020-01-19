package com.ris.ris.project.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String firstName;
    private String lastName;

    /*If we delete the user, we also want to delete his address*/
    /*Unidirectional link to address*/
    @OneToOne()
    private Address address;

    private LocalDate dateOfBirth;
    private String phoneNumber;
    private LocalDate accountCreationDate;
    private String email;
    private String password;

    @Lob
    @Column(columnDefinition = "BLOB")
    private Byte[] profileImage;

    /*If we delete the user, we also want to delete his auctionsForSale*/
    @OneToMany(mappedBy = "seller", targetEntity = Auction.class)
    private List<Auction> auctionsForSale;

    /*If we delete the user, we also want to delete his bought items*/
    @OneToMany(mappedBy = "buyer", targetEntity = Auction.class)
    private List<Auction> boughtItems;

    /*If we delete the user, we also want to delete his feedbacks*/
    @OneToMany(mappedBy = "seller", targetEntity = Feedback.class)
    private List<Feedback> feedbacksAsSeller;

    @OneToMany(mappedBy = "buyer", targetEntity = Feedback.class)
    private List<Feedback> feedbacksAsBuyer;

    @OneToMany(mappedBy = "bidder", targetEntity = Bid.class)
    private List<Bid> usersBids;

    //TODO Messages... :/

    public User() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Auction> getAuctionsForSale() {
        return auctionsForSale;
    }

    public void setAuctionsForSale(List<Auction> auctionsForSale) {
        this.auctionsForSale = auctionsForSale;
    }

    public List<Auction> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(List<Auction> boughtItems) {
        this.boughtItems = boughtItems;
    }

    public List<Feedback> getFeedbacksAsBuyer() {
        return feedbacksAsBuyer;
    }

    public void setFeedbacksAsBuyer(List<Feedback> feedbacksAsBuyer) {
        this.feedbacksAsBuyer = feedbacksAsBuyer;
    }

    public List<Feedback> getFeedbacksAsSeller() {
        return feedbacksAsSeller;
    }

    public void setFeedbacksAsSeller(List<Feedback> feedbacksAsSeller) {
        this.feedbacksAsSeller = feedbacksAsSeller;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public List<Bid> getUsersBids() {
        return usersBids;
    }

    public void setUsersBids(List<Bid> usersBids) {
        this.usersBids = usersBids;
    }
}
