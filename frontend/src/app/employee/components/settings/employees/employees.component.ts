import { Component, OnInit } from '@angular/core';
import {CompanyResourceService} from '../../../../shared/service/resource/company-resource.service';
import {UserService} from '../../../../shared/service/user.service';

@Component({
  selector: 'app-employee-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  isLoading = true;
  newEmployeeEmail : string;
  showInviteModal = false;
  private employees = [];

  constructor(
    private companyResourceService : CompanyResourceService,
    private userService : UserService
  ) { }

  ngOnInit() {
    this.getEmployees();
  }

  private getEmployees(){
    this.companyResourceService.getEmployees(this.userService.getCurrentCompany().id).subscribe(
      (employees : any) => {
        this.employees = employees;
      },
      (error) => {},
      () => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    );
  }

  addEmployeeToCompany(email : string){
    this.isLoading = true;
    this.showInviteModal = false;

    this.companyResourceService.addEmployee(this.userService.getCurrentCompany().id, email).subscribe(
      (employees : any) => {
        this.employees = employees;
      },
      error => {},
      () => {
        this.newEmployeeEmail = '';
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    );
  }

  removeEmployeeFromCompany(employeeId : number){
    this.isLoading = true;

    this.companyResourceService.removeEmployee(this.userService.getCurrentCompany().id, employeeId).subscribe(
      (employee : any) => {
        this.getEmployees();
      },
      (errr) => {},
      () => {
        this.newEmployeeEmail = '';
      }
    );
  }

}
