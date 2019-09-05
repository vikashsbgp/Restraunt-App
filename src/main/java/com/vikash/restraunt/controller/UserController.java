package com.vikash.restraunt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.restraunt.entities.User;
import com.vikash.restraunt.repos.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@PostMapping("/create")
	public User createUsers(@RequestBody User user) {
		
		LOGGER.info("Inside createUsers Method with parameter " + user);
		user.setPassword(encoder.encode(user.getPassword()));
		User response = userRepository.save(user);
		return response;
		
	}
	

	@GetMapping("/allUsers")
	public List<User> allUsers() {
		
		LOGGER.info("Inside allUsers method");
		return userRepository.findAll();
	}

}
