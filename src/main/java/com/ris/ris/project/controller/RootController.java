package com.ris.ris.project.controller;

import com.ris.ris.project.model.Auction;
import com.ris.ris.project.model.AuctionState;
import com.ris.ris.project.model.Categories;
import com.ris.ris.project.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RootController {
    @Autowired
    HttpSession session;

    @Autowired
    AuctionRepository ar;

    @RequestMapping(name = "/")
    public String index(Model model){
        //System.err.println(model.getAttribute("user")); //returns  null
        //System.err.println(session.getAttribute("user")); //returns user
        //A little bit hacky...
        model.addAttribute("user", session.getAttribute("user"));

        //A bit of a mouthful :)
        //Arrays.stream(Categories.values()).findAny().get()
        List<Auction> auctions = ar.findTop3ByCategoryAndAuctionStateOrderByDateTimeOfAuctionStart(Categories.ELECTRONICS, AuctionState.ACTIVE);
        model.addAttribute("newAuctions", auctions);

        return "/index";
    }
}
