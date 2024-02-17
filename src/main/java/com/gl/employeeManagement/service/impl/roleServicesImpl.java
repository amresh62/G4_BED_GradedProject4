package com.gl.employeeManagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.employeeManagement.model.roles;
import com.gl.employeeManagement.repository.roleRepository;
import com.gl.employeeManagement.service.roleServices;

@Service
public class roleServicesImpl implements roleServices {

    @Autowired
    private roleRepository rlRepository;

    @Override
    public List<roles> getRoles() {
        return rlRepository.findAll();
    }

    @Override
    public List<roles> getRolesByName(String rolename) {
        return rlRepository.findByName(rolename);
    }

    @Override
    public roles addRoles(roles roleData) {
        if (roleData != null)
            rlRepository.saveAndFlush(roleData);
        return roleData;
    }

    @Override
    public roles getRolesById(int id) {
        return rlRepository.findById(id).get();
    }

    @Override
    public List<roles> getRolesByName(List<roles> user_roles) {
        List<String> roleNames = user_roles.stream()
                .map(roles::getName)
                .collect(Collectors.toList());
        if (roleNames != null) {
            return rlRepository.findAllByNameIn(roleNames);
        } else {
            return null;
        }
    }

}
