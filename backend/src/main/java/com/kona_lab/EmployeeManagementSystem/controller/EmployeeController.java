package com.kona_lab.EmployeeManagementSystem.controller;
import java.awt.print.Pageable;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kona_lab.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.kona_lab.EmployeeManagementSystem.model.Employee;
import com.kona_lab.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@JsonIgnoreProperties(ignoreUnknown = true)
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {

//		if (firstName == null) {
//			return employeeRepository.findAll();
//		} else {
//			return employeeRepository.findByName(firstName, pageable);
//		}
		//System.out.println(firstName);
		return employeeRepository.findAll();

	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		// return employee;
       // employeeRepository.findByEmail(emailId);
		return employeeRepository.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
												   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setName(employeeDetails.getName());
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setContactNumber(employeeDetails.getContactNumber());
		employee.setGender(employeeDetails.getGender());
		employee.setDepartment(employeeDetails.getDepartment());
		employee.setProgrammingLanguage(employeeDetails.getProgrammingLanguage());
		employee.setPresentAddress(employeeDetails.getPresentAddress());
		employee.setProfilePicture(employeeDetails.getProfilePicture());

		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}