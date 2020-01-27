package com.ris.ris.project.controller;

import com.ris.ris.project.model.*;
import com.ris.ris.project.repository.AuctionRepository;
import com.ris.ris.project.repository.BidRepository;
import com.ris.ris.project.repository.FeedbackRepository;
import com.ris.ris.project.repository.UserRepository;
import com.ris.ris.project.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    HttpSession session;

    @Autowired
    UserRepository ur;

    @Autowired
    AuctionRepository ar;

    @Autowired
    BidRepository br;

    @Autowired
    FeedbackRepository fr;

    @Autowired
    ImageService imageService;


    @RequestMapping("/userProfile")
    public String userProfile(Model model){
        model.addAttribute("user", session.getAttribute("user"));
        return "/users/userProfile";
    }

    @RequestMapping("/userAuctions")
    public String userAuctions(Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("userAuctions", ar.findAllBySellerAndAuctionState(user, AuctionState.ACTIVE));
        return "/users/auctions/userAuctions";
    }


    @RequestMapping("/userAuctions/terminateAuction")
    public String terminateAuction(Long auctionID, Model model){
        User user = (User) session.getAttribute("user");

        Auction auction = ar.findById(auctionID).get();

        if(!auction.getBidders().isEmpty()){
            User highestBidder = auction.getBidders().first().getBidder();
            highestBidder.addBoughtItem(auction); //method sets association both ways
            ur.save(highestBidder);
        }

        auction.setDateTimeOfAuctionEnd(LocalDateTime.now());
        auction.setAuctionState(AuctionState.FINISHED);

        ar.save(auction);
        ur.save(user);

        return "/users/auctions/userAuctions";
    }

    @RequestMapping("/auctions/boughtItems")
    public String boughtItems(Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("boughtItems", ar.findAllByBuyer(user));
        model.addAttribute("feedbackGrades", FeedbackGrade.values());

        return "/users/auctions/boughtItems";
    }

    @RequestMapping("auctions/soldItems")
    public String soldItems(Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("soldItems", ar.findAllBySellerAndAuctionState(user, AuctionState.FINISHED));
        model.addAttribute("feedbackGrades", FeedbackGrade.values());

        return "users/auctions/soldItems";
    }

    @RequestMapping("/newAuction")
    public String newAuction(Model model){
        model.addAttribute("user", session.getAttribute("user"));

        model.addAttribute("categories", Categories.values());
        model.addAttribute("currencies", Currency.values());
        return "/users/auctions/newAuction";
    }

    @RequestMapping("/submitAuction")
    public String submitAuction(String title, String desc, Categories category, Currency currency, float initPrice, Model model){
        model.addAttribute("user", session.getAttribute("user"));

        Auction auction = new Auction();
        auction.setTitle(title);
        auction.setDescription(desc);
        auction.setCategory(category);
        auction.setCurrency(currency);
        auction.setInitialPrice(initPrice);
        auction.setSeller((User) session.getAttribute("user"));
        auction.setAuctionState(AuctionState.READY);

        ar.save(auction);

        model.addAttribute("auction", auction);
        return "/users/auctions/uploadImages";
    }

    @RequestMapping(value = "/submitAuction/uploadImages/auctionID={auctionID}", method = RequestMethod.POST)
    public String uploadAuctionImages(@PathVariable Long auctionID, @RequestParam("imageA") MultipartFile imgA, @RequestParam("imageA") MultipartFile imgB, @RequestParam("imageA") MultipartFile imgC,  Model model){
        model.addAttribute("user", session.getAttribute("user"));

        imageService.saveImageFilesForAuctions(auctionID, imgA, imgB, imgC);

        //Activate auction
        Auction auction = ar.findById(auctionID).get();
        auction.setAuctionState(AuctionState.ACTIVE);
        auction.setDateTimeOfAuctionStart(LocalDateTime.now());

        //re-save auction
        ar.save(auction);

        return "/users/userProfile";
    }

    @RequestMapping("/userBids")
    public String userBids(Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("bids", br.findAllByBidder(user));
        return "/users/auctions/bids/userBids";
    }

    @PostMapping("/auctions/placeBid")
    public String placeBid(Long auctionID, String amount, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        Auction auction = ar.findById(auctionID).get();

        if(auction.getSeller().getUserID().equals(user.getUserID())){ //TODO override equals in user
            //User in session and user seller cannot be the same
            model.addAttribute("message", "You cannot bid on your own auction! ... Play nice.");
            return "/users/auctions/bids/userBids";
        }

        float amountFloat;

        try{
            amountFloat = Float.parseFloat(amount);
        }catch(Exception e){
            String message = String.format("Illegal entered value... => %s <= is not a valid number.", amount);
            model.addAttribute("message", message);
            return "/users/auctions/bids/userBids";
        }

        if(auction.getBidders().isEmpty()){
            if(auction.getInitialPrice() > amountFloat){
                model.addAttribute("message", "You need to bid higher than the initial price");
                return "/users/auctions/bids/userBids";
            }
            Bid bid = new Bid();
            bid.setAmount(amountFloat);
            bid.setBidDateTime(LocalDateTime.now());
            bid.setAuction(auction);
            bid.setBidder(user);

            //And persist
            br.save(bid);
            auction.addBid(bid);
            ar.save(auction);

            model.addAttribute("message", "Bid successfully placed");
            return "/users/auctions/bids/userBids";
        }else{
            //Bids exist in this auction
            Bid currentMaxBid = auction.getBidders().first();
            if(currentMaxBid.getAmount() < amountFloat){
                Bid bid = new Bid();
                bid.setAmount(amountFloat);
                bid.setBidDateTime(LocalDateTime.now());
                bid.setAuction(auction);
                bid.setBidder(user);

                //And persist
                br.save(bid);
                auction.addBid(bid);
                ar.save(auction);

                model.addAttribute("message", "Bid successfully placed");
                return "/users/auctions/bids/userBids";
            }else{
                model.addAttribute("message","Your bid needs to be higher than the current max bid.");
                return "/users/auctions/bids/userBids";
            }
        }
    }

    @RequestMapping("/auctions/leaveFeedback")
    public String leaveFeedback(Long auctionID, FeedbackGrade feedbackGrade, String desc, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        Auction auction = ar.findById(auctionID).get();

        Feedback feedback = new Feedback();
        feedback.setAuction(auction);
        feedback.setDescription(desc);
        feedback.setGrade(feedbackGrade);

        //Persist feedback
        fr.save(feedback);

        if(user.getUserID().equals(auction.getSeller().getUserID())){
            //This case: seller leaving feedback to buyer
            auction.getBuyer().addFeedback(feedback);
            auction.setSellerLeftFeedback(true);
            ur.save(auction.getBuyer());
        }else{
            //This case: buyer leaving feedback to seller
            auction.getSeller().addFeedback(feedback);
            auction.setBuyerLeftFeedback(true);
            ur.save(auction.getSeller());
        }

        //re-save auction
        ar.save(auction);

        return "/users/userProfile";
    }

    @RequestMapping("/userFeedbacks")
    public String userFeedbacks(Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("feedbacks", fr.findAllByUsersContaining(user));
        return "/users/userFeedbacks";
    }
}
