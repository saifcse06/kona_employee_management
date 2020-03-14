package com.kona_lab.EmployeeManagementSystem.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kona_lab.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.kona_lab.EmployeeManagementSystem.model.Employee;
import com.kona_lab.EmployeeManagementSystem.repository.EmployeeRepository;
import com.kona_lab.EmployeeManagementSystem.services.FileuploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "http://localhost:4200")
@JsonIgnoreProperties(ignoreUnknown = true)
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

	@Autowired
	private FileuploadService fileUploadService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

     @PostMapping("/employees")
    //@RequestMapping(value=("/employees"),headers=("content-type=multipart/*"),method=RequestMethod.POST)
    public Object createEmployee(@Valid @RequestBody Employee employee) {
		//fileUploadService.storeFile(file);
        List<Employee> emailCheck = employeeRepository.findByEmailId(employee.getEmailId());
        List<Employee> mobileNumber = employeeRepository.findByContactNumber(employee.getContactNumber());

        if (emailCheck.isEmpty() && mobileNumber.isEmpty()) {
            return employeeRepository.save(employee);
        } else {
            return String.format("Email or Mobile Number Already Exist");
        }
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

    @GetMapping(value = "employees/search/{name}")
    public ResponseEntity<List<Employee>> findBySearch(@PathVariable String name) {
        try {
            List<Employee> employees = null;
            if (!name.isEmpty()) {

                employees = employeeRepository.findByname(name);
                System.out.println("1" + employees);
            }
            System.out.println("2" + name);
            if (employees.isEmpty()) {
                employees = employeeRepository.findByEmailId(name);
                System.out.println("3" + employees);
            } else if (employees.isEmpty()) {
                employees = employeeRepository.findByContactNumber(name);
            } else if (employees.isEmpty()) {
                employees = employeeRepository.findByDepartment(name);
            } else {
                return (ResponseEntity<List<Employee>>) employeeRepository.findAll();
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}