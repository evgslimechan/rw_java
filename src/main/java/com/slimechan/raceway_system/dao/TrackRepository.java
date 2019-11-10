package com.slimechan.raceway_system.dao;

import com.slimechan.raceway_system.model.NewsModel;
import com.slimechan.raceway_system.model.TrackModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TrackRepository extends MongoRepository<TrackModel, Integer> {

    @Query("{ '_id' : ?0 }")
    TrackModel find(int id);

    @Query(value="{ '_id' : ?0}", delete = true)
    Long remove(int id);

    default int lastId(){
        return findAll(new Sort(Sort.Direction.DESC, "_id")).size()>0?findAll(new Sort(Sort.Direction.DESC, "_id")).get(0).getId():0;
    }

}
