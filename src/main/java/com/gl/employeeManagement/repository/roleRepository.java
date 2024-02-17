package com.gl.employeeManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.employeeManagement.model.roles;

@Repository
public interface roleRepository extends JpaRepository<roles, Integer> {
    List<roles> findByName(String rolename);

    List<roles> findAllByNameIn(List<String> rolename);
}
