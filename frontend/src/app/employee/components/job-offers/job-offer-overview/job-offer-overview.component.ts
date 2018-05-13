import { Component, OnInit } from '@angular/core';
import { UrlProvider} from '../../../../shared/service/url-provider';
import {CustomHttpClient} from "../../../../shared/service/custom-http-client";
import {UserService} from "../../../../shared/service/user.service";

@Component({
  selector: 'app-job-offer-overview',
  templateUrl: './job-offer-overview.component.html',
  styleUrls: ['./job-offer-overview.component.css']
})
export class JobOfferOverviewComponent implements OnInit {

  constructor(private http : CustomHttpClient, private urlProvider : UrlProvider, private userService : UserService) {}

  public isLoading : boolean = true;
  public jobOffers : any = [];

  ngOnInit() {
    this.loadJobOffers();
  }

  loadJobOffers() {
    this.isLoading = true;
    this.http.get(this.urlProvider.jobOfferResource() + "?companyId=" + this.userService.getCurrentCompany().id).subscribe(
      data => this.jobOffers = data,
      error => console.log(error),
      () => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    )
  }

  deleteJobOffer(id : number) {
    this.http.delete(this.urlProvider.jobOfferResource() + "/" + id).subscribe(
      data => this.loadJobOffers(),
      error => console.log(error)
    )
  }

}
