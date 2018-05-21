import { Component, OnInit } from '@angular/core';
import {JobOfferRequest} from "../../../dtos/job-offer-request";
import {UserService} from "../../../../shared/service/user.service";
import {Router} from "@angular/router";
import {CompanyResourceService} from '../../../../shared/service/resource/company-resource.service';

@Component({
  selector: 'app-new-job-offer',
  templateUrl: './new-job-offer.component.html',
  styleUrls: ['./new-job-offer.component.css']
})
export class NewJobOfferComponent implements OnInit {

  jobOfferRequest = new JobOfferRequest();

  constructor(
    private userService : UserService,
    private router : Router,
    private companyResourceService : CompanyResourceService
  ) {}

  ngOnInit() {

  }

  addJobOffer() {

    this.jobOfferRequest.companyId = this.userService.getCurrentCompany().id;
    this.jobOfferRequest.locationId = 1;

    this.companyResourceService.addJobOffer(this.userService.getCurrentCompany().id, this.jobOfferRequest).subscribe(
      data => {
        this.router.navigate(['/employee/job-offers']);
      },
      error => console.log(error)
    );
  }

}
