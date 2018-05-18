import { Component, OnInit } from '@angular/core';
import {UserService} from "../../../../shared/service/user.service";
import {JobOfferResourceService} from '../../../../shared/service/resource/job-offer-resource.service';

@Component({
  selector: 'app-job-offer-overview',
  templateUrl: './job-offer-overview.component.html',
  styleUrls: ['./job-offer-overview.component.css']
})
export class JobOfferOverviewComponent implements OnInit {

  constructor(
    private jobOfferResourceService : JobOfferResourceService,
    private userService : UserService
  ) {}

  public isLoading : boolean = true;
  public jobOffers : any = [];

  ngOnInit() {
    this.loadJobOffers();
  }

  loadJobOffers() {
    this.isLoading = true;
    this.jobOfferResourceService.getJobOffers(this.userService.getCurrentCompany().id).subscribe(
      data => this.jobOffers = data,
      error => console.log(error),
      () => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    );
  }

  deleteJobOffer(id : number) {
    this.jobOfferResourceService.deleteJobOffer(id).subscribe(
      data => this.loadJobOffers(),
      error => console.log(error)
    );
  }

}
