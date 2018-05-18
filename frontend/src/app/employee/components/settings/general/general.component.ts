import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../../shared/service/user.service';
import {CompanyResourceService} from '../../../../shared/service/resource/company-resource.service';

@Component({
  selector: 'app-employee-general',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.css']
})
export class GeneralComponent implements OnInit {

  public isLoading : boolean = true;
  private company : any = {};

  constructor(private userService : UserService, private companyResourceService : CompanyResourceService) { }

  ngOnInit() {
    this.companyResourceService.getCompany(this.userService.getCurrentCompany().id).subscribe(
      (data) => this.company = data,
      (error) => console.log(error),
      () => setTimeout(() => { this.isLoading = false }, 250)
    )
  }
}
