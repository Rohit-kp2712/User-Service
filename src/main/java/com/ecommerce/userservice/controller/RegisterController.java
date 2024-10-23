package com.ecommerce.userservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.userservice.config.HeaderGenerator;
import com.ecommerce.userservice.entity.Users;
import com.ecommerce.userservice.service.UserService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
public class RegisterController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private HeaderGenerator headerGenerator;
    
    @PostMapping(value = "/registration")
    public ResponseEntity<Users> addUser(@RequestBody Users user, HttpServletRequest request){
    	if(user != null)
    		try {
    			userService.saveUser(user);
    			return new ResponseEntity<Users>(
    					user,
    					headerGenerator.getHeadersForSuccessPostMethod(request, user.getId()),
    					HttpStatus.CREATED);
    		}catch (Exception e) {
    			e.printStackTrace();
    			return new ResponseEntity<Users>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	return new ResponseEntity<Users>(HttpStatus.BAD_REQUEST);
    }
}
