package com.ris.ris.project.controller;

import com.ris.ris.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "authentication")
public class LoginController {
    @Autowired
    UserRepository ur;

    @RequestMapping("/login")
    public String login(Model model){
        return "/authentication/login";
    }

    @RequestMapping("/login-error")
    public String loginError(RedirectAttributes model){
        String message = "Login unsuccessful, please try again.";
        model.addFlashAttribute("loginError", message);
        return "redirect:/authentication/login";
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/";
    }
}
