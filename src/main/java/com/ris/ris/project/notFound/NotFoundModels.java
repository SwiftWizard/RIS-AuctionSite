package com.ris.ris.project.notFound;

import com.ris.ris.project.model.Auction;
import com.ris.ris.project.model.User;

public class NotFoundModels {
    public static final User USER;
    public static final Auction AUCTION;

    static{
        USER = new User();
        USER.setUsername("No user found for specified criteria!");

        AUCTION = new Auction();
        AUCTION.setTitle("No auction found for specified criteria!");
    }
}
