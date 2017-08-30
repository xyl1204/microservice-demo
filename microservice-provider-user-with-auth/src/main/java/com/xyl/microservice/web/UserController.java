package com.xyl.microservice.web;

import java.util.Collection;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xyl.microservice.entity.User;
import com.xyl.microservice.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/{id}")
	public User findById(@PathVariable("id") Long id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails user = (UserDetails) principal;
			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				LOGGER.info("当前用户是{}, 角色是{}", user.getUsername(), grantedAuthority.getAuthority());
			}
		}
		User user = this.userRepository.findOne(id);
		return user;
	}
}
