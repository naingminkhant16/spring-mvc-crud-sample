package com.moe.springmvccrud.service;

import com.moe.springmvccrud.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(Integer id);

    void save(Employee employee);

    void deleteById(Integer id);
}
