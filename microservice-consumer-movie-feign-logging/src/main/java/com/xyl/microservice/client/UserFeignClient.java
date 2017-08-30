package com.xyl.microservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyl.microservice.config.FeignLogConfiguration;
import com.xyl.microservice.entity.User;

@FeignClient(name="microservice-provider-user", configuration = FeignLogConfiguration.class)
public interface UserFeignClient {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable("id") Long id);
}
