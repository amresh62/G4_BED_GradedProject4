package com.gl.employeeManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.employeeManagement.model.employeeMaster;
import com.gl.employeeManagement.repository.employeeRepository;
import com.gl.employeeManagement.service.employeeServices;

@Service
public class employeeServicesImpl implements employeeServices {

    @Autowired
    private employeeRepository empRepository;

    @Override
    public List<employeeMaster> getAllEmployee() {
        return empRepository.findAll();
    }

    @Override
    public employeeMaster getEmployeeById(int id) {
        return empRepository.findById(id).get();
    }

    @Override
    public Integer deleteEmployeeById(int id) {
        empRepository.deleteById(id);
        return id;
    }

    @Override
    public employeeMaster addEmployee(employeeMaster empData) {
        if (empData != null)
            empRepository.saveAndFlush(empData);
        return empData;
    }

    @Override
    public List<employeeMaster> getEmployeeByFirstName(String firstName) {
        return empRepository.findByFirstName(firstName);
    }

    @Override
    public List<employeeMaster> getEmployeeSortedByFirstName(String order) {
        if ("asc".equalsIgnoreCase(order)) {
            return empRepository.findAllByOrderByFirstNameAsc();
        } else if ("desc".equalsIgnoreCase(order)) {
            return empRepository.findAllByOrderByFirstNameDesc();
        } else {
            throw new IllegalArgumentException("Invalid Sorting Method Supplied");
        }
    }
}
