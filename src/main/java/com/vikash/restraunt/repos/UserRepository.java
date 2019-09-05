package com.vikash.restraunt.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vikash.restraunt.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
	
	User findByEmail(String email);

}
