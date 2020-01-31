package com.ris.ris.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AboutController {
    @Autowired
    HttpSession session;

    @RequestMapping("about")
    public String about(Model model){
        model.addAttribute("user", session.getAttribute("user"));
        return "/about";
    }
}
