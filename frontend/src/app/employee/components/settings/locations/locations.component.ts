import { Component, OnInit } from '@angular/core';
import {UserService} from "../../../../shared/service/user.service";
import {CompanyResourceService} from '../../../../shared/service/resource/company-resource.service';

@Component({
  selector: 'app-employee-locations',
  templateUrl: './locations.component.html',
  styleUrls: ['./locations.component.css']
})
export class LocationsComponent implements OnInit {

  public locations : any = [];
  public isLoading : boolean = true;
  public showErrorPage : boolean = false;


  constructor(
    private userService : UserService,
    private companyResourceService : CompanyResourceService
  ) {
  }

  ngOnInit() {
    let companyId = this.userService.getCurrentUser().company.id;

    this.companyResourceService.getLocations(companyId).subscribe(
      (response) => {
        this.locations = response;
      },
      (error) => {

      },
      () => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    );
  }



}
