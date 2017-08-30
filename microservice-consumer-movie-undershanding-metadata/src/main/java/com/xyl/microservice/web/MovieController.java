package com.xyl.microservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.xyl.microservice.entity.User;

@RestController
public class MovieController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		User user = restTemplate.getForObject("http://localhost:8000/" + id, User.class);
		return user;
	}
	@GetMapping("/user-instance")
	public List<ServiceInstance> showInfo() {
		return this.discoveryClient.getInstances("microservice-provider-user");
	}
}
