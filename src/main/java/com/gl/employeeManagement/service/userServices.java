package com.gl.employeeManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.employeeManagement.model.users;

@Service
public interface userServices {
    users addUsers(users userData);

    List<users> getUsers();

    users getUserById(int id);
}
