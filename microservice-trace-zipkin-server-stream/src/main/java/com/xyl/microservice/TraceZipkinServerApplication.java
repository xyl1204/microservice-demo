package com.xyl.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@SpringBootApplication
@EnableZipkinStreamServer
public class TraceZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraceZipkinServerApplication.class, args);
	}
}
