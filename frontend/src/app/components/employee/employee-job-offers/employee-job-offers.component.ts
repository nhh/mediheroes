import { Component, OnInit } from '@angular/core';
import {CustomHttpClient} from "../../../services/custom-http-client";
import {UrlProvider} from "../../../services/url-provider";

@Component({
  selector: 'app-employee-job-offers',
  templateUrl: './employee-job-offers.component.html',
  styleUrls: ['./employee-job-offers.component.css']
})
export class EmployeeJobOffersComponent implements OnInit {

  constructor(private http : CustomHttpClient, private urlProvider : UrlProvider) {
  }

  isLoading = true;
  jobOffers = [];

  ngOnInit() {
    this.loadJobOffers()
  }

  loadJobOffers(){
    this.isLoading = true;
    this.http.get(this.urlProvider.jobOfferResource()).subscribe(
      data => this.jobOffers = data,
      error => console.log(error),
      complete => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    )
  }

}
