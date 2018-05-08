import { Component, OnInit } from '@angular/core';
import {CustomHttpClient} from "../../../../services/custom-http-client";
import {UrlProvider} from "../../../../services/url-provider";
import {UserService} from "../../../../services/auth/user.service";

@Component({
  selector: 'app-employee-locations',
  templateUrl: './employee-locations.component.html',
  styleUrls: ['./employee-locations.component.css']
})
export class EmployeeLocationsComponent implements OnInit {

  private locations : Array = [];
  private isLoading : boolean = true;
  private showErrorPage : boolean = false;


  constructor(
    private httpClient : CustomHttpClient,
    private urlProvider : UrlProvider,
    private userService : UserService
  ) {
  }

  ngOnInit() {
    let companyId = this.userService.getCurrentUser().company.id;
    this.httpClient.get(this.urlProvider.locationResource()+ '?companyId=' + companyId).subscribe(
      response => {
        setTimeout(() => {
          this.locations = response;
          this.isLoading = false;
        }, 250)
      },
      error => {
        setTimeout(() => {
          this.showErrorPage = true;
          this.isLoading = false;
        }, 250)
      }
    )
  }



}
