import { Component, OnInit } from '@angular/core';
import {UserService} from "../../../../shared/service/user.service";
import {CompanyResourceService} from '../../../../shared/service/resource/company-resource.service';

@Component({
  selector: 'app-job-offer-overview',
  templateUrl: './job-offer-overview.component.html',
  styleUrls: ['./job-offer-overview.component.css']
})
export class JobOfferOverviewComponent implements OnInit {

  constructor(
    private companyResourceService : CompanyResourceService,
    private userService : UserService
  ) {}

  public isLoading : boolean = true;
  public jobOffers : any = [];

  ngOnInit() {
    this.loadJobOffers();
  }

  loadJobOffers() {
    this.isLoading = true;
    this.companyResourceService.getJobOffers(this.userService.getCurrentCompany().id).subscribe(
      data => this.jobOffers = data,
      error => console.log(error),
      () => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    );
  }

  deleteJobOffer(jobOfferId : number) {
    this.companyResourceService.removeJobOffer(this.userService.getCurrentCompany().id, jobOfferId).subscribe(
      data => this.loadJobOffers(),
      error => console.log(error)
    );
  }

}
