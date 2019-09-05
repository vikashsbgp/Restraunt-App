package com.vikash.restraunt.controller;

import java.util.List;

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
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@PostMapping("/create")
	public User createUsers(@RequestBody User user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		User response = userRepository.save(user);
		return response;
		
	}
	

	@GetMapping("/allUsers")
	public List<User> allUsers() {
		return userRepository.findAll();
	}

}
