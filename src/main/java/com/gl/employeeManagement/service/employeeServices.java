package com.gl.employeeManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.employeeManagement.model.employeeMaster;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface employeeServices {

    List<employeeMaster> getAllEmployee();

    employeeMaster getEmployeeById(int id);

    Integer deleteEmployeeById(int id);

    employeeMaster addEmployee(employeeMaster empData);

    List<employeeMaster> getEmployeeByFirstName(String firstName);

    List<employeeMaster> getEmployeeSortedByFirstName(String order);

}
