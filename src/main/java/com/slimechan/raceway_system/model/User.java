package com.slimechan.raceway_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.awt.image.BufferedImage;

@Document(collection = "users")
public class User{

    public enum Role{
        USER,
        MANAGER,
        ADMIN;
    }

    @Id
    private String login;
    @Field
    private String password;
    @Field
    private Role role;
    @Field
    private String FIO;
    @Field
    private String avatar;
    @Field
    private long phone;


    private User(){}
    public User(String login, String password, String fio){
        this.login = login;
        this.password = password;
        this.FIO = fio;
        this.role = Role.USER;
    }

    public String getLogin() {
        return login;
    }

    public void setAvatar(BufferedImage image){}
    public void setAvatar(byte[] image){}

    public String getPassword(){return this.password;}
    public void setPassword(String hashPass){
        this.password= hashPass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}