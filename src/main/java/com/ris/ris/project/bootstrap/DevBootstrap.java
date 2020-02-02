package com.ris.ris.project.bootstrap;

import com.ris.ris.project.model.*;
import com.ris.ris.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    AuctionRepository ar;

    @Autowired
    FeedbackRepository fr;

    @Autowired
    UserRepository ur;

    @Autowired
    BidRepository br;

    @Autowired
    AddressRepository adrRep;

    @Autowired
    AuthorityRepository atr;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        //Addresses
        Address address1 = new Address();
        address1.setAddress("Strazilovska 55");
        address1.setCountry(Country.SERBIA);
        address1.setPostalCode(PostalCode.RS21000);
        address1.setCity("Novi Sad");
        adrRep.save(address1);


        Address address2 = new Address();
        address2.setAddress("Dimitrija Tucovica 12");
        address2.setCity("Subotica");
        address2.setCountry(Country.SERBIA);
        address2.setPostalCode(PostalCode.RS24000);
        adrRep.save(address2);

        Address address3 = new Address();
        address3.setAddress("Štrosmajerova 11");
        address3.setCity("Subotica");
        address3.setCountry(Country.SERBIA);
        address3.setPostalCode(PostalCode.RS24000);
        adrRep.save(address3);


        //Users
        User user1 = new User();
        user1.setFirstName("Marko");
        user1.setLastName("Markovic");
        user1.setUsername("marex");
        user1.setAccountCreationDate(LocalDate.now());
        user1.setDateOfBirth(LocalDate.of(1994, 10, 22));
        user1.setEmail("marko@email.com");
        user1.setPassword("1234");
        user1.setPhoneNumber("06212344321");
        user1.setAddress(address1);

        Authority authority1 = new Authority();
        user1.addAuthority(authority1);

        User user2 = new User();
        user2.setFirstName("Mirko");
        user2.setLastName("Mirkovic");
        user2.setUsername("mirex");
        user2.setAccountCreationDate(LocalDate.now());
        user2.setDateOfBirth(LocalDate.of(1998, 11, 2));
        user2.setEmail("mirko@email.com");
        user2.setPassword("1234");
        user2.setPhoneNumber("06412344321");
        user2.setAddress(address2);

        Authority authority2 = new Authority();
        user2.addAuthority(authority2);

        User user3 = new User();
        user3.setFirstName("Lara");
        user3.setLastName("Lalic");
        user3.setUsername("larex");
        user3.setAccountCreationDate(LocalDate.now());
        user3.setDateOfBirth(LocalDate.of(1985, 5, 25));
        user3.setEmail("lara@email.com");
        user3.setPassword("1234");
        user3.setPhoneNumber("06124356");
        user3.setAddress(address3);

        Authority authority3 = new Authority();
        user3.addAuthority(authority3);

        ur.save(user1);
        ur.save(user2);
        ur.save(user3);

        atr.save(authority1);
        atr.save(authority2);
        atr.save(authority3);

        //Auctions
        Auction auction1 = new Auction();
        auction1.setTitle("Nokia 3310");
        auction1.setDescription("Hi, im selling the legendary Nokia 3310 phone! In pristine condition, comes with charger.");
        auction1.setAuctionState(AuctionState.ACTIVE);
        auction1.setCategory(Categories.ELECTRONICS);
        auction1.setDateTimeOfAuctionStart(LocalDateTime.now());
        auction1.setSeller(user1);
        auction1.setCurrency(Currency.EURO);
        auction1.setInitialPrice(12.5111f);
        ar.save(auction1);

        Auction auction2 = new Auction();
        auction2.setTitle("Sony PS4");
        auction2.setDescription("Hi, im selling a Play Station 4 in good condition.");
        auction2.setAuctionState(AuctionState.ACTIVE);
        auction2.setCategory(Categories.ELECTRONICS);
        auction2.setDateTimeOfAuctionStart(LocalDateTime.now());
        auction2.setSeller(user1);
        auction2.setCurrency(Currency.EURO);
        auction2.setInitialPrice(55.555f);
        ar.save(auction2);

        Auction auction3 = new Auction();
        auction3.setTitle("Router");
        auction3.setDescription("Cisco router model: Cisco 2010 Connected Grid Router");
        auction3.setAuctionState(AuctionState.ACTIVE);
        auction3.setCategory(Categories.ELECTRONICS);
        auction3.setDateTimeOfAuctionStart(LocalDateTime.now());
        auction3.setSeller(user2);
        auction3.setCurrency(Currency.RSD);
        auction3.setInitialPrice(9999.901f);
        ar.save(auction3);

        Auction auction4 = new Auction();
        auction4.setTitle("Gibson guitar");
        auction4.setDescription("Hello, im selling a Gibson guitar in excellent condition");
        auction4.setAuctionState(AuctionState.ACTIVE);
        auction4.setCategory(Categories.MUSICAL_INSTRUMENTS);
        auction4.setDateTimeOfAuctionStart(LocalDateTime.now());
        auction4.setSeller(user3);
        auction4.setCurrency(Currency.EURO);
        auction4.setInitialPrice(121.112f);
        ar.save(auction4);

        Auction auction5 = new Auction();
        auction5.setTitle("WV Beetle engine");
        auction5.setDescription("Hi, im selling a engine for a WV Beetle from 1967 - 1500cc needs some fixing. PM me for more info.");
        auction5.setAuctionState(AuctionState.ACTIVE);
        auction5.setCategory(Categories.AUTO_PARTS);
        auction5.setDateTimeOfAuctionStart(LocalDateTime.now());
        auction5.setSeller(user2);
        auction5.setCurrency(Currency.EURO);
        auction5.setInitialPrice(67.25);
        ar.save(auction5);

        //Bids
        Bid bid = new Bid();
        bid.setAuction(auction1);
        bid.setBidDateTime(LocalDateTime.now());
        bid.setAmount(111.5120f);
        bid.setBidder(user2);
        br.save(bid);

        Bid higherBid = new Bid();
        higherBid.setBidder(user2);
        higherBid.setBidDateTime(LocalDateTime.now());
        higherBid.setAuction(auction1);
        higherBid.setAmount(300.2f);
        br.save(higherBid);

        Bid laraBid = new Bid();
        laraBid.setAuction(auction1);
        laraBid.setBidDateTime(LocalDateTime.now());
        laraBid.setAmount(312.2F);
        user3.addUserBid(laraBid);
        br.save(laraBid);



        List<Bid> bids = new ArrayList<>();
        bids.add(bid);
        bids.add(higherBid);

        List<Auction> la = new ArrayList<Auction>();
        la.add(auction1);

        System.out.println(auction1.getTitle() + " bids: " + auction1.getBidders());

        System.out.println(user1.getAuthorities());

        System.out.println(user1.getAuctionsForSale()); //Ovo ne moze
        System.out.println(user1.getFirstName() + " auctions for sale: " + ar.findAllBySeller(user1));
        System.out.println(user2.getFirstName() + " bids: \n  ->" + br.findAllByBidder(user2)
                                                            .stream()
                                                            .map(b -> (b.getAuction().getTitle() + " " + b.getAmount()))
                                                            .collect(Collectors.joining("\n  ->")));
    }
}
