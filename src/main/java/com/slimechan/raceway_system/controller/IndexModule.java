package com.slimechan.raceway_system.controller;

import javax.servlet.http.HttpServletRequest;

import com.slimechan.raceway_system.model.LoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexModule {

    private LoginUser user;

    @RequestMapping("/")
    public String onIndex(Model model, HttpServletRequest request){
        model.addAttribute("name", request.getRemoteAddr());
        return "index";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    private ModelAndView loginPage(ModelAndView model){
        return new ModelAndView("login", "loginUser", new LoginUser());
    }
    @RequestMapping(value="/loginUser", method=RequestMethod.POST)
    private RedirectView loginPage(ModelAndView model, @ModelAttribute("loginUser")LoginUser employee){
        this.user = employee;
        return new RedirectView("/");
    }

}