package com.ecommerce.userservice.service;

import java.util.List;

import com.ecommerce.userservice.entity.Users;



public interface UserService {
    List<Users> getAllUsers();
    Users getUserById(Long id);
    Users getUserByName(String userName);
    Users saveUser(Users user);
}
