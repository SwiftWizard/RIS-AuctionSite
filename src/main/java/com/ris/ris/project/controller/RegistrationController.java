package com.ris.ris.project.controller;

import com.ris.ris.project.model.Country;
import com.ris.ris.project.model.ZipCode;
import com.ris.ris.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    UserRepository ur;

    @RequestMapping(value = "/getCountriesAndZipCodes")
    public String getCountriesAndZipCodes(Model model){
        model.addAttribute("countries", Country.values());
        model.addAttribute("zipCodes", ZipCode.values());
        return "/newUser.jsp";
    }

    @RequestMapping(value = "/registration")
    public String registerUser(Model model){
        return "/";
    }
}
