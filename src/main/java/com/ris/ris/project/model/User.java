package com.ris.ris.project.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String firstName;
    private String lastName;
    private String username;

    /*Unidirectional link to address*/
    @OneToOne
    private Address address;

    private LocalDate dateOfBirth;
    private String phoneNumber;
    private LocalDate accountCreationDate;
    private String email;
    private String password;
    private LocalDateTime lastPasswordResetDateTime;
    private boolean emailVerified = true; //To make it verified by default, if there`s time implement it

    /*Bidirectional link to authorities (roles)*/
    //@ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "Authority", joinColumns = @JoinColumn(name = "userID", referencedColumnName = "userID"),
    //inverseJoinColumns = @JoinColumn(name = "authorityID", referencedColumnName = "authorityID"))
    @OneToMany(mappedBy = "user", targetEntity = Authority.class,fetch = FetchType.EAGER)
    private List<Authority> authorities  = new ArrayList<>();

    @Lob
    @Column(columnDefinition = "BLOB")
    private Byte[] profileImage;

    /*Bidirectional link to Auction as seller*/
    @OneToMany(mappedBy = "seller", targetEntity = Auction.class)
    private List<Auction> auctionsForSale = new ArrayList<>();

    /*Bidirectional link to Auction as buyer*/
    @OneToMany(mappedBy = "buyer", targetEntity = Auction.class)
    private List<Auction> boughtItems = new ArrayList<>();

    /*Bidirectional link to Feedback as seller*/
    @OneToMany(mappedBy = "seller", targetEntity = Feedback.class)
    private List<Feedback> feedbacksAsSeller = new ArrayList<>();

    /*Bidirectional link to Feedback as buyer*/
    @OneToMany(mappedBy = "buyer", targetEntity = Feedback.class)
    private List<Feedback> feedbacksAsBuyer = new ArrayList<>();

    /*Bidirectional link to Bid*/
    @OneToMany(mappedBy = "bidder", targetEntity = Bid.class)
    private List<Bid> usersBids = new ArrayList<>();

    //TODO Messages... :/

    public User() {
    }

    public User addAuthority(Authority authority){
        this.authorities.add(authority);
        authority.setUser(this);
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return Arrays.asList(new SimpleGrantedAuthority("STANDARD_USER"));
        return authorities;
    }

    @Override
    public String getUsername() {
        return username ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return emailVerified;
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

    public User addAuctionForSale(Auction auction){
        this.auctionsForSale.add(auction);
        auction.setSeller(this);
        return this;
    }

    public void setAuctionsForSale(List<Auction> auctionsForSale) {
        this.auctionsForSale = auctionsForSale;
    }

    public List<Auction> getBoughtItems() {
        return boughtItems;
    }

    public User addBoughtItem(Auction auction){
        this.boughtItems.add(auction);
        auction.setBuyer(this);
        return this;
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

    public User addFeedbackAsBuyer(Feedback feedback){
        this.feedbacksAsBuyer.add(feedback);
        feedback.setBuyer(this);
        return this;
    }

    public List<Feedback> getFeedbacksAsSeller() {
        return feedbacksAsSeller;
    }

    public void setFeedbacksAsSeller(List<Feedback> feedbacksAsSeller) {
        this.feedbacksAsSeller = feedbacksAsSeller;
    }

    public User addFeedbackAsSeller(Feedback feedback){
        this.feedbacksAsSeller.add(feedback);
        feedback.setSeller(this);
        return this;
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
        this.lastPasswordResetDateTime = LocalDateTime.now();
        this.password = password;//new BCryptPasswordEncoder().encode(password);
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

    public User addUserBid(Bid bid){
        this.usersBids.add(bid);
        bid.setBidder(this);
        return this;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLastPasswordResetDateTime() {
        return lastPasswordResetDateTime;
    }

    public void setLastPasswordResetDateTime(LocalDateTime lastPasswordResetDateTime) {
        this.lastPasswordResetDateTime = lastPasswordResetDateTime;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

}
