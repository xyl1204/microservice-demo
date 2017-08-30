package com.xyl.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyl.microservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
