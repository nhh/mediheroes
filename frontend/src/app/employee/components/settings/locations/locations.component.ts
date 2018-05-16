import { Component, OnInit } from '@angular/core';
import {CustomHttpClient} from "../../../../shared/service/custom-http-client";
import { UrlProvider} from '../../../../shared/service/url-provider';
import {UserService} from "../../../../shared/service/user.service";

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
    private httpClient : CustomHttpClient,
    private urlProvider : UrlProvider,
    private userService : UserService
  ) {
  }

  ngOnInit() {
    let companyId = this.userService.getCurrentUser().company.id;
    this.httpClient.get(this.urlProvider.locationResource()).subscribe(
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
