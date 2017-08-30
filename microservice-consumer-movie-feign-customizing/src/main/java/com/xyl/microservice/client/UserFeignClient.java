package com.xyl.microservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyl.microservice.config.FeignConfiguration;
import com.xyl.microservice.entity.User;

import feign.Param;
import feign.RequestLine;

@FeignClient(name="microservice-provider-user", configuration = FeignConfiguration.class)
public interface UserFeignClient {

	@RequestLine("GET /{id}")
	public User findById(@Param("id") Long id);
}
