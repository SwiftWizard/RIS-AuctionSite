package com.ris.ris.project.bootstrap;

import com.ris.ris.project.model.*;
import com.ris.ris.project.repository.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    AuctionRepository ar;

    @Autowired
    FeedBackRepository fr;

    @Autowired
    UserRepository ur;

    @Autowired
    BidRepository br;

    @Autowired
    AddressRepository adrRep;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        //Addresses
        Address address1 = new Address();
        address1.setAddress("Strazilovska 55");
        address1.setCountry(Country.SERBIA);
        address1.setZipCode(ZipCode.RS21000);
        address1.setCity("Novi Sad");
        adrRep.save(address1);


        Address address2 = new Address();
        address2.setAddress("Dimitrija Tucovica 12");
        address2.setCity("Subotica");
        address2.setCountry(Country.SERBIA);
        address2.setZipCode(ZipCode.RS24000);
        adrRep.save(address2);


        //Users
        User user1 = new User();
        user1.setFirstName("Marko");
        user1.setLastName("Markovic");
        user1.setAccountCreationDate(LocalDate.now());
        user1.setDateOfBirth(LocalDate.of(1994, 10, 22));
        user1.setEmail("marko@email.com");
        user1.setPassword("MySuperSecretPassword");
        user1.setPhoneNumber("06212344321");
        user1.setAddress(address1);

        User user2 = new User();
        user2.setFirstName("Mirko");
        user2.setLastName("Mirkovic");
        user2.setAccountCreationDate(LocalDate.now());
        user2.setDateOfBirth(LocalDate.of(1998, 11, 2));
        user2.setEmail("mirko@email.com");
        user2.setPassword("ExtraSecretPassword");
        user2.setPhoneNumber("06412344321");
        user2.setAddress(address2);


        ur.save(user1);
        ur.save(user2);

        //Auctions
        Auction auction1 = new Auction();
        auction1.setTitle("Nokia 3310");
        auction1.setDescription("Hi, im selling the legendary Nokia 3310 phone! In pristine condition, comes with charger.");
        auction1.setAuctionState(AuctionState.ACTIVE);
        auction1.setCategory(Categories.ELECTRONICS);
        auction1.setDateTimeOfAuctionStart(LocalDateTime.now());
        auction1.setSeller(user1);
        auction1.setInitialPrice(12.5f);
        auction1.setDateTimeOfAuctionEnd(LocalDateTime.now().plusDays(7));
        ar.save(auction1);

        //Bids
        Bid bid = new Bid();
        bid.setAuction(auction1);
        bid.setBidDateTime(LocalDateTime.now());
        bid.setAmount(111.50f);
        bid.setBidder(user2);
        br.save(bid);

        Bid higherBid = new Bid();
        higherBid.setBidder(user2);
        higherBid.setBidDateTime(LocalDateTime.now());
        higherBid.setAuction(auction1);
        higherBid.setAmount(300.2f);
        br.save(higherBid);


        //Nista ne radi... jej...

        System.out.println(auction1.getBidders());
        System.out.println(user1.getAuctionsForSale());
        System.out.println(user2.getUsersBids());
    }
}
