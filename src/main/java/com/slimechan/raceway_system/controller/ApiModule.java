package com.slimechan.raceway_system.controller;

import com.slimechan.raceway_system.dao.UserRepository;
import com.slimechan.raceway_system.manages.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping(value="/api", method = {RequestMethod.GET,RequestMethod.POST})
public class ApiModule{

    @Autowired
    private SessionManager manager;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/login")
    public ResponseEntity login(@RequestParam Map<String, String> params, @RequestHeader HttpHeaders headers) throws URISyntaxException {
        if(params.containsKey("login") & params.containsKey("password")){
            if(params.get("login").equals("slimechan_")&
               params.get("password").equals("pass123")){
                return ResponseEntity.ok("{\"response\":\""+manager.generateToken(20)+"\"}");
            }
        }
        HttpHeaders h = new HttpHeaders();
        h.add("Location", "/error");
        return new ResponseEntity<String>(h, HttpStatus.FOUND);
    }
    @RequestMapping("/register")
    public ResponseEntity register(@RequestParam Map<String, String> params, @RequestHeader HttpHeaders headers){

        if(userRepository.existsById(params.get("login"))){
            return ResponseEntity.ok("{\"response\":\"User exist\"}");
        }else{
            return ResponseEntity.ok().build();
        }

        //return ResponseEntity.notFound().build();
    }


}