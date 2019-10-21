package com.slimechan.raceway_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.awt.image.BufferedImage;

@Document(collection = "users")
public class User{

    public enum Role{
        ANONIM,
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
    @Field
    private String mail;


    private User(){}
    public User(String login, String password, String fio){}

    public void setAvatar(BufferedImage image){}
    public void setAvatar(byte[] image){}

}