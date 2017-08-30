package com.xyl.microservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xyl.microservice.entity.User;

@RestController
public class MovieController {

	private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@HystrixCommand(fallbackMethod = "findByIdFallback", commandProperties = {
		      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
			, @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")}
	, threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "1")
	, @HystrixProperty(name = "maxQueueSize", value = "10")})
	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		User user = restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
		return user;
	}
	
	@GetMapping("/log-instance")
	public void logUserInstance() {
		ServiceInstance instance = loadBalancerClient.choose("microservice-provider-user");
		LOGGER.info("{}:{}:{}", instance.getServiceId(), instance.getHost(), instance.getPort());
	}
	
	public User findByIdFallback(Long id) {
		User user = new User();
		user.setId(-1L);
		user.setName("默认用户");
		return user;
	}
}
