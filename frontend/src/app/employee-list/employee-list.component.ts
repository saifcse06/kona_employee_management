import { Component, OnInit } from '@angular/core';

import { Observable } from "rxjs";
import { EmployeeService } from "../employee.service";
import { Employee } from "../employee";
import { Router } from '@angular/router';
import { FormGroup, FormControl , ReactiveFormsModule } from '@angular/forms';  

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: Observable<Employee[]>;
  employee: Employee = new Employee();

  constructor(private employeeService: EmployeeService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }
  form = new FormGroup({  
    name : new FormControl() 
  });  

  reloadData(employee = null) {
    this.employees = this.employeeService.getEmployeesList();
  }
  searchForm(searchInfo)  
  {  
        this.employee.name = this.Name.value;    
        this.reloadData(this.employee);  
  }  
  get Name()  
  {  
    return this.form.get('name');  
  }  
  

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  employeeDetails(id: number){
    this.router.navigate(['details', id]);
  }
  employeeUpdate(id:number){
    this.router.navigate(['update', id]);
  }
}