package com.slimechan.raceway_system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.slimechan.raceway_system.manages.Http;
import com.slimechan.raceway_system.manages.NewsManager;
import com.slimechan.raceway_system.manages.TrackManager;
import com.slimechan.raceway_system.manages.UserManager;
import com.slimechan.raceway_system.model.LoginUser;

import com.slimechan.raceway_system.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.HashMap;

@Controller
public class IndexModule {

    @Autowired
    private NewsManager newsManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private TrackManager trackManager;

    @RequestMapping("/")
    public RedirectView onIndex(Model model, HttpServletRequest request, HttpSession session){
        if(session.getAttribute("token")==null) return new RedirectView("/login");
        return new RedirectView("/news");
    }

    @RequestMapping("/news")
    public String newsPage(Model model, HttpServletRequest request, HttpSession session){

        System.out.println("rem adddr "+request.getRemoteAddr());
        if(session.getAttribute("token")==null){

            UriComponents redirectUri = UriComponentsBuilder.fromPath("/login").build().encode();
            return "redirect:" + redirectUri.toString();
        }
        model.addAttribute("news", newsManager.getPage());
        return "news";
    }

    @RequestMapping("/tracks")
    public String tracksPage(Model model, HttpSession session){
        if(session.getAttribute("token")==null||session.getAttribute("user")==null){
            UriComponents redirectUri = UriComponentsBuilder.fromPath("/login").build().encode();
            return "redirect:" + redirectUri.toString();
        }
        model.addAttribute("tracks", trackManager.getPage());
        return "tracks";
    }

    @RequestMapping("/personal")
    public String personPage(Model model, HttpSession session){
        if(session.getAttribute("token")==null||session.getAttribute("user")==null){

            UriComponents redirectUri = UriComponentsBuilder.fromPath("/login").build().encode();
            return "redirect:" + redirectUri.toString();
        }else{
            if(((User)session.getAttribute("user")).getRole()== User.Role.MANAGER||
              ((User)session.getAttribute("user")).getRole()== User.Role.ADMIN){

                model.addAttribute("users", userManager.getAll());
                return "personal";

            }
        }
        UriComponents redirectUri = UriComponentsBuilder.fromPath("/").build().encode();
        return "redirect:" + redirectUri.toString();
    }

    @RequestMapping("/events")
    public String eventsPage(Model model, HttpSession session){
        if(session.getAttribute("token")==null||session.getAttribute("user")==null){
            UriComponents redirectUri = UriComponentsBuilder.fromPath("/login").build().encode();
            return "redirect:" + redirectUri.toString();
        }
        return "events";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    private ModelAndView loginPage(ModelAndView model, HttpSession session){
        if(session.getAttribute("token")!=null&session.getAttribute("user")!=null){
            new ModelAndView("redirect:/news");
        }
        return new ModelAndView("login", "user", new LoginUser());
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
    @RequestMapping(value="/loginUser", method=RequestMethod.POST)
    private RedirectView loginPage(ModelAndView model, @ModelAttribute("user")LoginUser user, HttpSession session){

        JSONObject o = null;
        try {
            o = Http.sendPost("http://localhost/api/login", new HashMap<String, Object>(){{put("login",user.login);put("password",user.pass);}});
            switch(o.getString("response")){
                case "user not found":
                    break;
                case "bad password":
                    break;
                default:
                    session.setAttribute("token", o.getString("response"));

                    session.setAttribute("user", userManager.getUser(user.login).get());
                    break;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new RedirectView("/");
    }

}