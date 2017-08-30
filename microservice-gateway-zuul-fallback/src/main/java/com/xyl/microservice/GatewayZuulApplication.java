package com.xyl.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;

import com.xyl.microservice.fallback.UserFallbackProvider;

@EnableZuulProxy
@SpringBootApplication
public class GatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayZuulApplication.class, args);
	}
	
	@Bean
	public ZuulFallbackProvider userFallbackProvider() {
		return new UserFallbackProvider();
	}
}
