package com.ecommerce.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class UserServiceApplication {

	public static Logger logger=LoggerFactory.getLogger(UserServiceApplication.class);
	
	@PostConstruct
	void init()
	{
		logger.info("Application Started...");
		logger.info("Application Started3...");
	}
	public static void main(String[] args) {
		logger.info("Application Executed");
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
