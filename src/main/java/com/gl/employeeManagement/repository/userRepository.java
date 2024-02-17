package com.gl.employeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.employeeManagement.model.users;

@Repository
public interface userRepository extends JpaRepository<users, Integer> {
    users findByUsername(String username);

}
