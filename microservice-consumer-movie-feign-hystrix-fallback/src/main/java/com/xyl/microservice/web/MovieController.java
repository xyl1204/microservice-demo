package com.xyl.microservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xyl.microservice.client.UserFeignClient;
import com.xyl.microservice.entity.User;

@RestController
public class MovieController {

	@Autowired
	private UserFeignClient userFeignClient;
	
	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		User user = userFeignClient.findById(id);
		return user;
	}
	@PostMapping("/saveUser")
	public String saveUser(@RequestBody User user) {
		return userFeignClient.saveUser(user);
	}
}
