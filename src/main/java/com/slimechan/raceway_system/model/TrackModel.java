package com.slimechan.raceway_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("tracks")
public class TrackModel {
    public enum TrackConfig{
        RACE,
        DRAG,
        DRIFT;
    }

    @Id
    private int id;
    @Field
    private String name;
    @Field
    private String description;
    @Field
    private TrackConfig config;

    private TrackModel(){}
    public TrackModel(int id, String name, String description, TrackConfig cfg){
        this.id = id;
        this.name = name;
        this.description = description;
        this.config = cfg;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TrackConfig getConfig() {
        return config;
    }

    public void setConfig(TrackConfig config) {
        this.config = config;
    }

    public String getConfigColor(){
        switch (config) {
            case RACE:
                return "red";
            case DRAG:
                return "green";
            case DRIFT:
                return "blue";
        }
        return "linear-gradient(red, black)";
    }
}
