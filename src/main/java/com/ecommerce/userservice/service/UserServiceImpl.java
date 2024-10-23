package com.ecommerce.userservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.userservice.entity.UserRole;
import com.ecommerce.userservice.entity.Users;
import com.ecommerce.userservice.repository.UserRepository;
import com.ecommerce.userservice.repository.UserRoleRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public Users getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Users saveUser(Users user) {
    	
        user.setActive(1);
        UserRole role = userRoleRepository.findUserRoleByRoleName("ROLE_USER");
        user.setRole(role);
        return userRepository.save(user);
    }
}
