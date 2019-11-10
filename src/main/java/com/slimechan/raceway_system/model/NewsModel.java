package com.slimechan.raceway_system.model;

import com.slimechan.raceway_system.utils.Base64Image;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.List;

@Document("news")
public class NewsModel {

    @Id
    private int Id;
    @Field
    private String title;
    @Field
    private String text;
    @Field("image")
    private String base64Image;

    private NewsModel(){}

    public NewsModel(int id ,String title, String text, byte[] image, String imgType){
        this.Id = id;
        this.title = title;
        this.text = text;
        this.base64Image = Base64Image.encodeToString(image, imgType);
    }

    public int getId(){return Id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public byte[] getImage(){
        return Base64Image.decodeToImage(base64Image);
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

}
