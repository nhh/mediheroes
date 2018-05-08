import { Component, OnInit } from '@angular/core';
import {CustomHttpClient} from "../../../../services/custom-http-client";
import {UrlProvider} from "../../../../services/url-provider";

@Component({
  selector: 'app-employee-general',
  templateUrl: './employee-general.component.html',
  styleUrls: ['./employee-general.component.css']
})
export class EmployeeGeneralComponent implements OnInit {

  private isLoading : boolean = true;
  private company : any = {};

  constructor(private http : CustomHttpClient, private urlProvider : UrlProvider) { }

  ngOnInit() {
    this.http.get(this.urlProvider.currentCompanyResource()).subscribe(
      (data) => this.company = data,
      (error) => console.log(error),
      (complete) => setTimeout(() => { this.isLoading = false }, 250)
    )
  }

}
