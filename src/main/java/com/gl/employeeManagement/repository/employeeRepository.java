package com.gl.employeeManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.employeeManagement.model.employeeMaster;

@Repository
public interface employeeRepository extends JpaRepository<employeeMaster, Integer> {
    List<employeeMaster> findByFirstName(String firstName);

    List<employeeMaster> findAllByOrderByFirstNameAsc();

    List<employeeMaster> findAllByOrderByFirstNameDesc();
}
