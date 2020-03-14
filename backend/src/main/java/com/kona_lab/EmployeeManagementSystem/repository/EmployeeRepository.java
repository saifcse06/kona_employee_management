package com.kona_lab.EmployeeManagementSystem.repository;

import com.kona_lab.EmployeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
  //  List<Employee> findByEmail(String email);
}
