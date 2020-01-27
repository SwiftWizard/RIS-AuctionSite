package com.ris.ris.project.repository;

import com.ris.ris.project.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findAllBySeller(User seller);

    List<Auction> findAllBySellerAndAuctionState(User seller, AuctionState state);

    List<Auction> findAllByAuctionState(AuctionState state);

    List<Auction> findAllByBuyer(User buyer);

    //For landing page..
    List<Auction> findTop3ByCategoryAndAuctionStateOrderByDateTimeOfAuctionStart(Categories category, AuctionState state);

    //Line 20 - 32 used for filtering auctions for controller on mapping 'view/auction/filter'
    List<Auction> findAllByTitleContainingAndCategoryAndSeller_Address_CountryAndAuctionState(String title, Categories category, Country country, AuctionState state);

    List<Auction> findAllByCategoryAndSeller_Address_CountryAndAuctionState(Categories category, Country country, AuctionState state);

    List<Auction> findAllByTitleContainingAndSeller_Address_CountryAndAuctionState(String title, Country country, AuctionState state);

    List<Auction> findAllByTitleContainingAndCategoryAndAuctionState(String title, Categories category, AuctionState state);

    List<Auction> findAllBySeller_Address_CountryAndAuctionState(Country country, AuctionState state);

    List<Auction> findAllByTitleContainingAndAuctionState(String title, AuctionState state);

    List<Auction> findAllByCategoryAndAuctionState(Categories category, AuctionState state);
}
