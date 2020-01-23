package com.ris.ris.project.model;

import javax.persistence.*;
import java.time.LocalDateTime;

//This is probably a NO-NO, but I hope it will do for now...
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;


    private String message;

    private LocalDateTime dateTimeOfMessageSent;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
