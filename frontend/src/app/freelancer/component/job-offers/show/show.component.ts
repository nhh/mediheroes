import { Component, OnInit } from '@angular/core';
import {JobOfferResourceService} from '../../../../shared/service/resource/job-offer-resource.service';
import {ActivatedRoute} from '@angular/router';
import {CompanyResourceService} from '../../../../shared/service/resource/company-resource.service';
import {mergeMap} from 'rxjs/operators';

@Component({
  selector: 'app-show',
  templateUrl: './show.component.html',
  styleUrls: ['./show.component.css']
})
export class ShowComponent implements OnInit {

  jobOffer : any;
  company : any;
  isLoading = true;

  constructor(
    private companyResourceService : CompanyResourceService,
    private jobOfferResourceService : JobOfferResourceService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.jobOfferResourceService.getOneJobOffer(+params['id']).pipe(
        mergeMap((jobOfferResponse : any) => {
          this.jobOffer = jobOfferResponse;
          return this.companyResourceService.getCompany(jobOfferResponse.companyId);
        })
      ).subscribe(
        (company) => this.company = company,
        (error) => {},
        () => {
          setTimeout(() => {this.isLoading = false}, 250)
        }
      )
    });
  }

}
