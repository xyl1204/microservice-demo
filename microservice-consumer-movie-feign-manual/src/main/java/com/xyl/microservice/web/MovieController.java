package com.xyl.microservice.web;

import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xyl.microservice.client.UserFeignClient;
import com.xyl.microservice.entity.User;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {

	private UserFeignClient userUserFeignClient;
	
	private UserFeignClient adminUserFeignClient;
	
	public MovieController(Decoder decoder, Encoder encoder,
			Client client, Contract contract) {
		userUserFeignClient = Feign.builder().client(client)
				.encoder(encoder).decoder(decoder).contract(contract)
				.requestInterceptor(new BasicAuthRequestInterceptor("user", "password2"))
				.target(UserFeignClient.class, "http://microservice-provider-user/");
		
		adminUserFeignClient = Feign.builder().client(client)
				.encoder(encoder).decoder(decoder).contract(contract)
				.requestInterceptor(new BasicAuthRequestInterceptor("admin", "password1"))
				.target(UserFeignClient.class, "http://microservice-provider-user/");
	}
	
	@GetMapping("/user-user/{id}")
	public User findByIdUser(@PathVariable Long id) {
		User user = userUserFeignClient.findById(id);
		return user;
	}
	@GetMapping("/user-admin/{id}")
	public User findByIdAdmin(@PathVariable Long id) {
		User admin = adminUserFeignClient.findById(id);
		return admin;
	}
}
