package com.slimechan.raceway_system.controller;

import com.slimechan.raceway_system.dao.UserRepository;
import com.slimechan.raceway_system.manages.*;
import com.slimechan.raceway_system.model.TrackModel;
import com.slimechan.raceway_system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping(value="/api", method = {RequestMethod.GET,RequestMethod.POST})
public class ApiModule{

    /*

        /api/mail?page=<id>

        /api/news?page=<id>
        /api/news/add
        /api/news/update?id=<id>
        /api/news/remove?id=<id>

        /api/tracks?page=<id>
        /api/tracks/add
        /api/tracks/update?id=<id>
        /api/tracks/remove?id=<id>

        /api/login

     */

    @Autowired
    private SessionManager manager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private NewsManager newsManager;
    @Autowired
    private EventManager eventManager;
    @Autowired
    private TrackManager trackManager;

    @RequestMapping("/login")
    public ResponseEntity login(@RequestParam Map<String, String> params,
                                @RequestHeader HttpHeaders headers,
                                HttpSession session) throws URISyntaxException {
            if(headers.containsKey("login") & headers.containsKey("password")){
                if(userManager.userExists(headers.get("login").get(0))){
                    if(userManager.checkPass(headers.get("password").get(0), userManager.getUser(headers.get("login").get(0)).get().getPassword())){

                        return ResponseEntity.ok("{\"response\":\""+manager.generateToken(25)+"\"}");
                    }else{
                        return ResponseEntity.ok("{\"response\":\"bad password\"}");
                    }
                }
                else return ResponseEntity.ok("{\"response\":\"user not found\"}");
            }

        HttpHeaders h = new HttpHeaders();
        h.add("Location", "/");
        return new ResponseEntity<String>(h, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ResponseEntity news(@RequestParam Map<String, String> params,
                               @RequestHeader HttpHeaders headers,
                               HttpSession session){

        if(session.getAttribute("token")!=null) {
            
        }
        HttpHeaders h = new HttpHeaders();
        h.add("Location", "/");
        return new ResponseEntity<String>(h, HttpStatus.FOUND);
    }

    @CrossOrigin
    @RequestMapping(value = "/news/add", method = RequestMethod.POST)
    public ResponseEntity newsAdd(@RequestParam Map<String, String> params,
                               @RequestHeader HttpHeaders headers,
                                  HttpServletRequest request){

        try{
            String token = request.getParameter("token");

            String ipAddress = request.getRemoteAddr();
            System.out.println("rem H "+ipAddress);

            if(manager.containsUser(request.getRemoteAddr())){
                System.out.println("Contains "+request.getRemoteAddr());
            }else{
                System.out.println("not Contains "+request.getRemoteAddr());
            }
        }catch (Exception e){
            return ResponseEntity.ok("bad token");
        }
        try
        {
            String title = request.getParameter("title");
            String text = request.getParameter("text");
            System.out.println(title+" "+text);
            if(request instanceof MultipartHttpServletRequest){
                System.out.println("Request with file");
                MultipartHttpServletRequest r = (MultipartHttpServletRequest) request;

                newsManager.addNews(title, text, r.getFile("file").getBytes());
            }else{
                newsManager.addNews(title, text, new byte[1]);
            }


        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.ok("No args set");
        }

        return ResponseEntity.ok("OK OB");
    }
    @CrossOrigin
    @RequestMapping(value = "/news/edit", method = RequestMethod.POST)
    public ResponseEntity newsUpdate(@RequestParam Map<String, String> params,
                                     @RequestHeader HttpHeaders headers,
                                     HttpServletRequest request){
        try{
            String token = request.getParameter("token");


            if(manager.containsUser(request.getRemoteAddr())){

            }
        }catch (Exception e){
            return ResponseEntity.ok("bad token");
        }
        try
        {
            String title = request.getParameter("title");
            String text = request.getParameter("text");
            System.out.println(title+" "+text);
        }
        catch(Exception ex)
        {
            return ResponseEntity.ok("No args set");
        }
        return ResponseEntity.ok("OK OB");
    }

    @CrossOrigin
    @RequestMapping(value = "/news/remove", method = RequestMethod.POST)
    public ResponseEntity newsRemove(@RequestParam Map<String, String> params,
                                  @RequestHeader HttpHeaders headers,
                                  HttpServletRequest request){
        try
        {
            String i = request.getParameter("id");
            int id = Integer.parseInt(i);
            newsManager.removeNews(id);
            System.out.println("News removed "+id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.ok("No args set");
        }

        return ResponseEntity.ok("OK OB");
    }
    @CrossOrigin
    @RequestMapping(value = "/personal/add", method = RequestMethod.POST)
    public ResponseEntity personAdd(@RequestParam Map<String, String> params,
                                     @RequestHeader HttpHeaders headers,
                                     HttpServletRequest request){
        try{
            String token = request.getParameter("token");


            if(manager.containsUser(request.getRemoteAddr())){

            }
        }catch (Exception e){
            return ResponseEntity.ok("bad token");
        }
        try
        {
            String id = request.getParameter("id");
            String pas = request.getParameter("password");
            String fio = request.getParameter("fio");
            String phone = request.getParameter("phone");
            String role = request.getParameter("role");
            User u = new User(id, pas, fio);
            u.setPhone(Long.parseLong(phone));
            u.setRole(User.Role.valueOf(role));
            userManager.save(u);
            System.out.println("User "+id+" created");
        }
        catch(Exception ex)
        {
            return ResponseEntity.ok("No args set");
        }
        return ResponseEntity.ok("OK OB");
    }
    @CrossOrigin
    @RequestMapping(value = "/personal/edit", method = RequestMethod.POST)
    public ResponseEntity personEdit(@RequestParam Map<String, String> params,
                                     @RequestHeader HttpHeaders headers,
                                     HttpServletRequest request){
        try{
            String token = request.getParameter("token");


            if(manager.containsUser(request.getRemoteAddr())){

            }
        }catch (Exception e){
            return ResponseEntity.ok("bad token");
        }
        try
        {
            String id = request.getParameter("id");
            String pas = request.getParameter("password");
            String fio = request.getParameter("fio");
            String phone = request.getParameter("phone");
            String role = request.getParameter("role");
            System.out.println(id+" "+pas+" "+fio+" "+phone+" "+role);
            User u = new User(id, pas, fio);
            u.setPhone(Long.parseLong(phone));
            u.setRole(User.Role.valueOf(role));
            userManager.save(u);
        }
        catch(Exception ex)
        {
            return ResponseEntity.ok("No args set");
        }
        return ResponseEntity.ok("OK OB");
    }
    @CrossOrigin
    @RequestMapping(value = "/personal/remove", method = RequestMethod.GET)
    public ResponseEntity personalRemove(@RequestParam Map<String, String> params,
                                     @RequestHeader HttpHeaders headers,
                                     HttpServletRequest request){
        try
        {
            String i = request.getParameter("id");
            userManager.removeUser(i);
            System.out.println("User removed "+i);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.ok("No args set");
        }

        return ResponseEntity.ok("OK OB");
    }

    @CrossOrigin
    @RequestMapping(value = "/track/add", method = RequestMethod.POST)
    public ResponseEntity trackAdd(@RequestParam Map<String, String> params,
                                    @RequestHeader HttpHeaders headers,
                                    HttpServletRequest request){
        try{
            String token = request.getParameter("token");


            if(manager.containsUser(request.getRemoteAddr())){

            }
        }catch (Exception e){
            return ResponseEntity.ok("bad token");
        }
        try
        {
            String name = request.getParameter("name");
            String desc = request.getParameter("desc");
            String config = request.getParameter("config");

            trackManager.addTrack(name, desc, TrackModel.TrackConfig.valueOf(config));

            System.out.println("Track "+name+" created");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.ok("No args set");
        }
        return ResponseEntity.ok("OK OB");
    }
    @CrossOrigin
    @RequestMapping(value = "/track/edit", method = RequestMethod.POST)
    public ResponseEntity trackEdit(@RequestParam Map<String, String> params,
                                     @RequestHeader HttpHeaders headers,
                                     HttpServletRequest request){
        try{
            String token = request.getParameter("token");


            if(manager.containsUser(request.getRemoteAddr())){

            }
        }catch (Exception e){
            return ResponseEntity.ok("bad token");
        }
        try
        {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String desc = request.getParameter("desc");
            String config = request.getParameter("config");


        }
        catch(Exception ex)
        {
            return ResponseEntity.ok("No args set");
        }
        return ResponseEntity.ok("OK OB");
    }
    @CrossOrigin
    @RequestMapping(value = "/track/remove", method = RequestMethod.GET)
    public ResponseEntity trackRemove(@RequestParam Map<String, String> params,
                                         @RequestHeader HttpHeaders headers,
                                         HttpServletRequest request){
        try
        {
            String i = request.getParameter("id");
            userManager.removeUser(i);
            System.out.println("Track removed "+i);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ResponseEntity.ok("No args set");
        }

        return ResponseEntity.ok("OK OB");
    }
}