package com.ris.ris.project.model;

//This is probably a NO-NO, but I hope it will do for now...
public class Message {
    private User sender;
    private User receiver;
    private String message;

    public Message() {
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
}
