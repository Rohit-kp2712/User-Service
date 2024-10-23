package com.ecommerce.userservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.userservice.config.HeaderGenerator;
import com.ecommerce.userservice.entity.Users;
import com.ecommerce.userservice.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private HeaderGenerator headerGenerator;
    
    @GetMapping (value = "/users")
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> users =  userService.getAllUsers();
        if(!users.isEmpty()) {
        	return new ResponseEntity<List<Users>>(
        		users,
        		headerGenerator.getHeadersForSuccessGetMethod(),
        		HttpStatus.OK);
        }
        return new ResponseEntity<List<Users>>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "/users", params = "name")
    public ResponseEntity<Users> getUserByName(@RequestParam("name") String userName){
    	Users user = userService.getUserByName(userName);
    	if(user != null) {
    		return new ResponseEntity<Users>(
    				user,
    				headerGenerator.
    				getHeadersForSuccessGetMethod(),
    				HttpStatus.OK);
    	}
        return new ResponseEntity<Users>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Long id){
        Users user = userService.getUserById(id);
        if(user != null) {
    		return new ResponseEntity<Users>(
    				user,
    				headerGenerator.
    				getHeadersForSuccessGetMethod(),
    				HttpStatus.OK);
    	}
        return new ResponseEntity<Users>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }

    @PostMapping (value = "/users")
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
