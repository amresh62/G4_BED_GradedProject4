package com.gl.employeeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.employeeManagement.model.employeeMaster;
import com.gl.employeeManagement.service.employeeServices;

@RestController
@RequestMapping("/employees")
public class employeeController {

    @Autowired
    private employeeServices empServices;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(empServices.getAllEmployee(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        try {

            return new ResponseEntity<>(empServices.getEmployeeById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{firstName}")
    public ResponseEntity<?> findbyname(@PathVariable String firstName) {
        try {
            return new ResponseEntity<>(empServices.getEmployeeByFirstName(firstName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sort")
    public ResponseEntity<?> findbysorting(@RequestParam String order) {
        try {
            return new ResponseEntity<>(empServices.getEmployeeSortedByFirstName(order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody employeeMaster empData) {
        try {

            return new ResponseEntity<>(empServices.addEmployee(empData), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody employeeMaster empData) {
        try {
            employeeMaster emp = empServices.getEmployeeById(empData.getId());
            emp.setId(empData.getId());
            emp.setFirstName(empData.getFirstName());
            emp.setLastName((empData.getLastName()));
            emp.setEmail(empData.getEmail());
            return new ResponseEntity<>(empServices.addEmployee(emp), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>("Deleted Empoyee Id - " + empServices.deleteEmployeeById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
