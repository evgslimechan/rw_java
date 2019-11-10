package com.slimechan.raceway_system.manages;

import com.slimechan.raceway_system.dao.NewsRepository;
import com.slimechan.raceway_system.model.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NewsManager {

    @Autowired
    private NewsRepository repository;

    private NewsManager(){
    }

    public void addNews(String title, String text, byte[] photo){
        NewsModel nm = new NewsModel((int)(repository.lastId()+1), title, text, photo, "png");
        repository.save(nm);
    }
    public void removeNews(int id){
        NewsModel nm = repository.find(id);
        if(nm!=null){
            System.out.println(nm.getTitle());
            repository.remove(id);
        }else System.out.println("news with id "+id+" not found");
    }
    public void updateNews(int id, String title, String text, byte[] photo){}

    public List<List<NewsModel>> getPage(){
        if(repository.count()==0) return new ArrayList<>();
        List<List<NewsModel>> news = new ArrayList<>();
        int lines = (int)Math.ceil(repository.count()/6);
        for(int y = 0; y<lines; y++){
            List<NewsModel> line = repository.findAll().subList(y*6, y*6+6>(int)repository.count()?(int)repository.count():y*6+6);
            news.add(line);
        }
        return news;
    }
    public int getPageCount(){return (int)Math.ceil(repository.count()/30);}

}
