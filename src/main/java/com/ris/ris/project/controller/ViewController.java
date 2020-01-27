package com.ris.ris.project.controller;

import com.ris.ris.project.model.*;
import com.ris.ris.project.notFound.NotFoundModels;
import com.ris.ris.project.repository.AuctionRepository;
import com.ris.ris.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    HttpSession session;

    @Autowired
    AuctionRepository ar;

    @Autowired
    UserRepository ur;

    @RequestMapping("/auctions/all")
    public String allAuctions(Model model){
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("auctions", ar.findAllByAuctionState(AuctionState.ACTIVE));
        model.addAttribute("categories", Categories.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("currencies", Currency.values());
        return "view/auctions";
    }

    @RequestMapping("/auctions/filter")
    public String filterAuctions(String title, Categories category, Country country, Model model){
        model.addAttribute("user", session.getAttribute("user"));
        System.err.println("\n\n\nTitle: '" + title + "'\ncategory:'" + category + "'\n\n\n");
        //So that user can re-choose
        model.addAttribute("categories", Categories.values());
        model.addAttribute("countries", Country.values());
        model.addAttribute("currencies", Currency.values());

        //Brace yourself

        if(!title.equals("") && category != null && country != null){ //everything present
            //by all three criteria
            model.addAttribute("auctions", ar.findAllByTitleContainingAndCategoryAndSeller_Address_CountryAndAuctionState(
                    title, category, country, AuctionState.ACTIVE
            ));
        }else if(title.equals("")  && category != null && country != null){ //no title
            //By category and country
            model.addAttribute("auctions", ar.findAllByCategoryAndSeller_Address_CountryAndAuctionState(
                    category, country, AuctionState.ACTIVE
            ));
        }else if(category == null && !title.equals("") && country != null){ //no category
            //by title and country
            model.addAttribute("auctions", ar.findAllByTitleContainingAndSeller_Address_CountryAndAuctionState(
                    title, country, AuctionState.ACTIVE
            ));
        }
        else if(country == null && category != null && !title.equals("")){ //no country
            //by title and category
            model.addAttribute("auctions", ar.findAllByTitleContainingAndCategoryAndAuctionState(
                    title, category, AuctionState.ACTIVE
            ));
        }else if(title.equals("") && category == null && country != null){ //no title and category
            //Just by country
            model.addAttribute("auctions", ar.findAllBySeller_Address_CountryAndAuctionState(
                    country, AuctionState.ACTIVE
            ));
        }
        else if(category == null && country == null && !title.equals("")){ //no category and country
            //Just by title
            model.addAttribute("auctions", ar.findAllByTitleContainingAndAuctionState(
                    title, AuctionState.ACTIVE
            ));
        }
        else if(title.equals("") && country == null && category != null) { //no title and country
            //Just by category
            model.addAttribute("auctions", ar.findAllByCategoryAndAuctionState(
                    category, AuctionState.ACTIVE
            ));
        }
        else{ //All fields null
            return "redirect:/view/auctions/all";
        }
        return "view/auctions";
    }


    @RequestMapping(value = "/auctions/auction", method = RequestMethod.GET)
    public String auction(@RequestParam Long id, Model model){
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("auction", ar.findById(id).orElse(NotFoundModels.AUCTION));
        return "view/auction";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userDetails(@RequestParam Long id, Model model){
        model.addAttribute("userView", ur.findById(id).orElse(NotFoundModels.USER));
        return "view/user";
    }
}
