import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { Router } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee = new Employee();
  model: any = {};
  submitted = false;
  selectedFile: File;
  message:string;
  
  constructor(private employeeService: EmployeeService,
    private router: Router) { }

  ngOnInit() {
    this.message = null;
  }
 //Gets called when the user selects an image
 public onFileChanged(event) {
  //Select File
  this.selectedFile = event.target.files[0];
}

  newEmployee(): void {
    this.submitted = false;
    this.employee = new Employee();
  }

  save() {
    var employee = new FormData();
    employee.append('file',this.employee.profilePicture);
    const headers = new HttpHeaders({
      'Content-Type': 'multipart/form-data',
      Accept: 'application/json'
    });
    //console.log(this.employee.profilePicture);
    this.employeeService.createEmployee(this.employee)
      .subscribe(data =>   this.gotoList(), error =>  this.message = error.error.text);
    this.employee = new Employee();
   
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/employees']);
  }
}
