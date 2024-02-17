package com.gl.employeeManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gl.employeeManagement.config.MyUserDetails;
import com.gl.employeeManagement.config.WebSecurity;
import com.gl.employeeManagement.model.users;
import com.gl.employeeManagement.repository.userRepository;
import com.gl.employeeManagement.service.roleServices;
import com.gl.employeeManagement.service.userServices;

@Service
public class userServicesImpl implements userServices, UserDetailsService {

    @Autowired
    private userRepository usrRepository;

    @Autowired
    private roleServices rlServices;

    @Autowired
    private WebSecurity web;

    @Override
    public users addUsers(users userData) {
        if (userData != null) {
            users user = new users();
            user.setUsername(userData.getUsername());
            user.setPassword(web.passwordEncoder().encode(userData.getPassword()));
            user.setRoles(rlServices.getRolesByName(userData.getRoles()));
            usrRepository.saveAndFlush(user);
        }
        return userData;
    }

    @Override
    public List<users> getUsers() {

        return usrRepository.findAll();
    }

    @Override
    public users getUserById(int id) {
        return usrRepository.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        users user = usrRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(user);
    }
}
