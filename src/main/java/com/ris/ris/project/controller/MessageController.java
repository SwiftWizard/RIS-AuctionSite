package com.ris.ris.project.controller;

import com.ris.ris.project.model.Auction;
import com.ris.ris.project.repository.AuctionRepository;
import com.ris.ris.project.repository.MessageRepository;
import com.ris.ris.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("users/messages")
public class MessageController {
    @Autowired
    HttpSession session;

    @Autowired
    AuctionRepository ar;

    @Autowired
    MessageRepository mr;


    @RequestMapping("/sendMessage")
    public String sendMesssage(@RequestParam Long auctionID, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        Auction auction = ar.findById(auctionID).get();

        model.addAttribute("messages", mr.findAllByAuction(auction));

        return "users/messages/sendMessage";
    }
}
