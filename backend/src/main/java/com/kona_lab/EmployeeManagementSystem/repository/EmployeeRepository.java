package com.kona_lab.EmployeeManagementSystem.repository;

import com.kona_lab.EmployeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByname(String name);
    List<Employee> findByEmailId(String emailId);
    List<Employee> findByContactNumber(String contactNumber);
    List<Employee> findByDepartment(String department);
}
