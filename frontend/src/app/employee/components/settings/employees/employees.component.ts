import { Component, OnInit } from '@angular/core';
import {CustomHttpClient} from '../../../../shared/service/custom-http-client';
import {UrlProvider} from '../../../../shared/service/url-provider';

@Component({
  selector: 'app-employee-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  private isLoading = true;
  private employees = [];
  private newEmployeeEmail : string;
  private showInviteModal = false;

  constructor(
    private http : CustomHttpClient,
    private urlProvider : UrlProvider,

  ) { }

  ngOnInit() {
    this.getEmployeesOfCompany();
  }

  getEmployeesOfCompany(){
    this.http.get(this.urlProvider.currentCompanyEmployeesResource()).subscribe(
      (employees : any) => {
        this.employees = employees;
      },
      (error) => {},
      () => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    )
  }

  addEmployeeToCompany(email : string){
    this.isLoading = true;
    this.showInviteModal = false;
    this.http.post(this.urlProvider.currentCompanyEmployeesResource(), email).subscribe(
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
    )
  }

  removeEmployeeFromCompany(employeeId : number){
    this.isLoading = true;
    this.http.delete(this.urlProvider.currentCompanyEmployeesResource() + '/' + employeeId).subscribe(
      (employee : any) => {
        this.getEmployeesOfCompany();
      },
      (errr) => {},
      () => {
        this.newEmployeeEmail = '';
      }
    )
  }

}
