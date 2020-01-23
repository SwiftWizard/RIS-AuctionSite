package com.ris.ris.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @RequestMapping(name = "/")
    public String index(){
        return "/index";
    }
}
