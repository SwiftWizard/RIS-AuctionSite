package com.ris.ris.project.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Message implements Comparable<Message>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageID;

    @OneToOne
    private User sender;

    @OneToOne
    private User receiver;

    //Talking about auction:
    @OneToOne
    private Auction auction;

    @ManyToMany(mappedBy = "messages", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    private String message;

    private LocalDateTime dateTimeOfMessageSent;

    public Message() {
    }

    @Override
    public int compareTo(Message other) {
        return this.dateTimeOfMessageSent.compareTo(other.dateTimeOfMessageSent);
    }

    public Long getMessageID() {
        return messageID;
    }

    public void setMessageID(Long messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTimeOfMessageSent() {
        return dateTimeOfMessageSent;
    }

    public void setDateTimeOfMessageSent(LocalDateTime dateTimeOfMessageSent) {
        this.dateTimeOfMessageSent = dateTimeOfMessageSent;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Message addUser(User user){
        users.add(user);
        return this;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
