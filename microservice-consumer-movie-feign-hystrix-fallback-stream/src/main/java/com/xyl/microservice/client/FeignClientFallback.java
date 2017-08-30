package com.xyl.microservice.client;

import org.springframework.stereotype.Component;

import com.xyl.microservice.entity.User;

@Component
public class FeignClientFallback implements UserFeignClient {

	@Override
	public User findById(Long id) {
		User user = new User();
		user.setId(-1L);
		user.setUsername("默认用户");
		return user;
	}

	@Override
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
