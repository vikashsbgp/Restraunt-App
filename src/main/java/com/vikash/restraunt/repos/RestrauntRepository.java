package com.vikash.restraunt.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vikash.restraunt.entities.Restraunt;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrauntRepository extends MongoRepository<Restraunt, Integer> {
	
	Restraunt findByName(String name);
	
	List<Restraunt> findByCity(String city);
	
	
}
