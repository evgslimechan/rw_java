package com.slimechan.raceway_system.manages;

import com.slimechan.raceway_system.dao.UserRepository;
import com.slimechan.raceway_system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager {

    @Autowired
    UserRepository repository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Optional<User> getUser(String login){
        return repository.findById(login);
    }

    public void addUser(String login, String pass, String fio){
        User u = new User(login, hashPassword(pass), fio);
        repository.save(u);
    }
    public List<User> getAll(){
        return repository.findAll();
    }
    public boolean userExists(String login){
        return repository.existsById(login);
    }
    public void removeUser(String login){repository.deleteById(login);}


    public boolean checkPass(String rawPass, String hashPass){
        return passwordEncoder.matches(rawPass, hashPass);
    }

    public String hashPassword(String rawPass){
        return passwordEncoder.encode(rawPass);
    }

    public void save(User u) {
        repository.save(u);
    }
}
