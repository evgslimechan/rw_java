package com.slimechan.raceway_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApiModule{

    @RequestMapping("/api/login")
    public String login(ModelAndView model){
        return "";
    }

}