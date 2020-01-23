package com.ris.ris.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "authentication")
public class LoginController {
    @Autowired
    UserController ur;

    @RequestMapping("/login")
    public String login(){
        return "/authentication/login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", "Login unsuccessful, please try again.");
        return "/authentication/login";
    }
}
