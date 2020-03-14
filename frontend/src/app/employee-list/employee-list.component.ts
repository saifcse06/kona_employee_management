import { Component, OnInit } from '@angular/core';

import { Observable } from "rxjs";
import { EmployeeService } from "../employee.service";
import { Employee } from "../employee";
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: Observable<Employee[]>;
  employee: Employee = new Employee();

  name: string;
  emailId: string;
  contactNumber: string;
  department: string;

  constructor(private employeeService: EmployeeService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
    this.name = '';
    this.emailId = '';
    this.contactNumber = '';
    this.department = '';
  } 

  reloadData() {
    this.employees = this.employeeService.getEmployeesList();
  } 

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id)
      .subscribe(
        data => { 
          this.reloadData();
        },
        error => console.log(error));
  }

  private searchEmployees() { 
    this.employees = this.employeeService.getEmployeeBySearch(this.name); 
  }

  onSubmit() {
    this.searchEmployees();
  }

  employeeDetails(id: number){
    this.router.navigate(['details', id]);
  }

  employeeUpdate(id:number){
    this.router.navigate(['update', id]);
  }
}