import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { Router } from '@angular/router';
import { EmployeeService } from '../employee.service';

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
  
  constructor(private employeeService: EmployeeService,
    private router: Router) { }

  ngOnInit() {
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
    console.log(this.employee.profilePicture);
    this.employeeService.createEmployee(this.employee)
      .subscribe(data => console.log(data), error => console.log(error));
    this.employee = new Employee();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/employees']);
  }
}
