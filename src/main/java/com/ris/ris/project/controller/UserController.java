package com.ris.ris.project.controller;

import com.ris.ris.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    UserRepository ur;

    @RequestMapping("/userProfile")
    public String userProfile(){
        return "/users/userProfile";
    }
}
