package com.gl.employeeManagement.service;

import java.util.List;

import com.gl.employeeManagement.model.roles;

public interface roleServices {
    List<roles> getRoles();

    List<roles> getRolesByName(String rolename);

    roles addRoles(roles roleData);

    roles getRolesById(int id);

    List<roles> getRolesByName(List<roles> user_roles);
}