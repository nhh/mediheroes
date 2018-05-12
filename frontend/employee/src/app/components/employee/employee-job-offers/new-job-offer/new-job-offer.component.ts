import { Component, OnInit } from '@angular/core';
import {CustomHttpClient} from "../../../../services/custom-http-client";
import {JobOfferRequest} from "../../../../dtos/job-offer-request";
import {UserService} from "../../../../services/auth/user.service";
import {UrlProvider} from "../../../../services/url-provider";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-job-offer',
  templateUrl: './new-job-offer.component.html',
  styleUrls: ['./new-job-offer.component.css']
})
export class NewJobOfferComponent implements OnInit {

  jobOfferRequest = new JobOfferRequest();

  constructor(private http : CustomHttpClient, private userService : UserService, private urlProvider : UrlProvider, private router : Router) {}

  ngOnInit() {

  }

  addJobOffer(jobOfferRequest : JobOfferRequest) {
    jobOfferRequest.companyId = this.userService.getCurrentCompany().id;
    jobOfferRequest.locationId = 1;
    this.http.post(this.urlProvider.jobOfferResource(), jobOfferRequest).subscribe(
      data => {
        this.router.navigate(['/employee/job-offers/overview']);
      },
      error => console.log(error)
    )
  }


  loadLocations() {
    // load locations
  }


}
