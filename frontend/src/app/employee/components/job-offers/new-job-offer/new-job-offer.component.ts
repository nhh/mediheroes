import { Component, OnInit } from '@angular/core';
import {JobOfferRequest} from "../../../dtos/job-offer-request";
import {UserService} from "../../../../shared/service/user.service";
import {Router} from "@angular/router";
import {JobOfferResourceService} from '../../../../shared/service/resource/job-offer-resource.service';

@Component({
  selector: 'app-new-job-offer',
  templateUrl: './new-job-offer.component.html',
  styleUrls: ['./new-job-offer.component.css']
})
export class NewJobOfferComponent implements OnInit {

  jobOfferRequest = new JobOfferRequest();

  constructor(private userService : UserService, private router : Router, private jobOfferResourceService : JobOfferResourceService) {}

  ngOnInit() {

  }

  addJobOffer() {

    this.jobOfferRequest.companyId = this.userService.getCurrentCompany().id;
    this.jobOfferRequest.locationId = 1;

    this.jobOfferResourceService.createJobOffer(this.jobOfferRequest).subscribe(
      data => {
        this.router.navigate(['/employee/job-offers']);
      },
      error => console.log(error)
    );
  }

}
