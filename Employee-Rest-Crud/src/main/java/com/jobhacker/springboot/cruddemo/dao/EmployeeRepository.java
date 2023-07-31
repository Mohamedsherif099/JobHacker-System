package com.jobhacker.springboot.cruddemo.dao;

import com.jobhacker.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {



}
