package com.slimechan.raceway_system.manages;

import com.slimechan.raceway_system.dao.TrackRepository;
import com.slimechan.raceway_system.model.TrackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackManager {

    @Autowired
    private TrackRepository repository;

    public void addTrack(String name, String description, TrackModel.TrackConfig config){
        TrackModel tm = new TrackModel((int)(repository.lastId()+1), name, description, config);
        repository.save(tm);
    }
    public void removeTrack(int id){
        TrackModel nm = repository.find(id);
        if(nm!=null){
            repository.remove(id);
        }else System.out.println("track with id "+id+" not found");
    }
    public void updateTrack(int id, String name, String description, TrackModel.TrackConfig config){}

    public List<List<TrackModel>> getPage(){

        if(repository.count()==0) return new ArrayList<>();
        List<List<TrackModel>> news = new ArrayList<>();
        int lines = (int)Math.ceil(repository.count()/6)<1?1:(int)Math.ceil(repository.count()/6);
        for(int y = 0; y<lines; y++){
            List<TrackModel> line = repository.findAll().subList(y*6, y*6+6>(int)repository.count()?(int)repository.count():y*6+6);
            news.add(line);
        }
        return news;
    }
    public int getPageCount(){return (int)Math.ceil(repository.count()/30);}

}
