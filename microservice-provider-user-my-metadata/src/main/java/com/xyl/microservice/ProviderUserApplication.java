package com.xyl.microservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.serializer.support.DeserializingConverter;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.DiscoveryClient.DiscoveryClientOptionalArgs;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderUserApplication.class, args);
	}
	@Bean
	public DiscoveryClientOptionalArgs discoveryClientOptionalArgs() {
		DiscoveryClientOptionalArgs discoveryClientOptionalArgs = new DiscoveryClientOptionalArgs();
		List<ClientFilter> additionalFilters = new ArrayList<>();
		additionalFilters.add(new HTTPBasicAuthFilter("admin", "admin1234"));
		discoveryClientOptionalArgs.setAdditionalFilters(additionalFilters);
		return discoveryClientOptionalArgs;
	}
}
