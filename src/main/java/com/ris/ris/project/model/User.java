package com.ris.ris.project.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @NotNull(message = "Please fill in your first name")
    private String firstName;

    @NotNull(message = "Please fill in your last name")
    private String lastName;

    @NotNull(message = "Please fill in your username")
    private String username;

    /*Unidirectional link to address*/
    @OneToOne
    private Address address;

    //@NotBlank(message = "Please fill in your date of birth") //Causes shutdown?!
    private LocalDate dateOfBirth;

    //Does not work initiates shutdown - @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(?\\d{3}\\)?\\d{3}-?\\d{4}|\\(?\\d{3}\\)?\\d{3}/?\\d{4}", message = "Please fill in a valid phone number")
    @NotNull(message = "Please fill in your phone number")
    private String phoneNumber;

    private LocalDate accountCreationDate;

    @NotNull(message = "Please fill in your email address")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull(message = "Please fill in your password")
    @Size(min=6, max = 100, message = "Password length must be at least 6 characters long")
    private String password;

    private LocalDateTime lastPasswordResetDateTime;

    private boolean emailVerified = true; //To make it verified by default, if there`s time implement it


    @OneToMany(mappedBy = "user", targetEntity = Authority.class, fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    @Lob
    @Column(columnDefinition = "BLOB")
    private Byte[] profileImage;

    /*Bidirectional link to Auction as seller*/
    @OneToMany(mappedBy = "seller", targetEntity = Auction.class, fetch = FetchType.EAGER)
    private Set<Auction> auctionsForSale = new HashSet<>();

    /*Bidirectional link to Auction as buyer*/
    @OneToMany(mappedBy = "buyer", targetEntity = Auction.class, fetch = FetchType.EAGER)
    private Set<Auction> boughtItems = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_feedback",
            joinColumns = @JoinColumn(name = "user_userID"),
            inverseJoinColumns = @JoinColumn(name = "feedback_feedbackID"))
    private Set<Feedback> feedbacks = new HashSet<>();

    /*Link to Bid*/
    @OneToMany(mappedBy = "bidder", targetEntity = Bid.class, fetch = FetchType.EAGER)
    private Set<Bid> usersBids = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_messages",
            joinColumns = @JoinColumn(name = "user_userID"),
            inverseJoinColumns = @JoinColumn(name = "message_messageID"))
    List<Message> messages = new ArrayList<>();

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

    public Set<Auction> getAuctionsForSale() {
        /*
          Fixes:
            *org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role ...(role)
            *could not initialize proxy - no Session

            *Note: no proper solution for this problem exists
         */
        Set<Auction> auctions = auctionsForSale;
        for(Auction a : auctions){
            a.getBidders().size();
        }
        return auctions;
    }

    public User addAuctionForSale(Auction auction){
        this.auctionsForSale.add(auction);
        auction.setSeller(this);
        return this;
    }

    public void setAuctionsForSale(Set<Auction> auctionsForSale) {
        this.auctionsForSale = auctionsForSale;
    }

    public Set<Auction> getBoughtItems() {
        Set<Auction> auctions = boughtItems;
        for(Auction a : auctions){
            a.getBidders().size();
        }
        return auctions;
    }

    public User addBoughtItem(Auction auction){
        this.boughtItems.add(auction);
        auction.setBuyer(this);
        return this;
    }

    public void setBoughtItems(Set<Auction> boughtItems) {
        this.boughtItems = boughtItems;
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
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public Byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public Set<Bid> getUsersBids() {
        return usersBids;
    }

    public void setUsersBids(Set<Bid> usersBids) {
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

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public User addFeedback(Feedback feedback){
        this.feedbacks.add(feedback);
        return this;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public User addMessage(Message message){
        messages.add(message);
        return this;
    }
}
