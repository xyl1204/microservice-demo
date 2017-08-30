package com.xyl.microservice.client;

import org.slf4j.Logger;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyl.microservice.entity.User;

import feign.hystrix.FallbackFactory;

@FeignClient(name="microservice-provider-user", fallbackFactory = FeignClientFallbackFactory.class)
public interface UserFeignClient {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable("id") Long id);
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@RequestBody User user);
	
}
@Component
class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FeignClientFallbackFactory.class);
	@Override
	public UserFeignClient create(Throwable cause) {
		return new UserFeignClient() {
			
			@Override
			public String saveUser(User user) {
				return null;
			}
			
			@Override
			public User findById(Long id) {
				LOGGER.info("fallback; reason was： " + cause);
				User user = new User();
				user.setId(-1L);
				user.setName("默认用户");
				return user;
			}
		};
	}
}

