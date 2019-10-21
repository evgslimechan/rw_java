package com.slimechan.raceway_system.dao;

import com.slimechan.raceway_system.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {



}
