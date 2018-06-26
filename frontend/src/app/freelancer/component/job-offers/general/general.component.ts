import { Component, OnInit } from '@angular/core';
import {NotificationService} from '../../../../shared/service/notification.service';
import {CompanyResourceService} from '../../../../shared/service/resource/company-resource.service';
import {JobOfferResourceService} from '../../../../shared/service/resource/job-offer-resource.service';
import {ActivatedRoute} from '@angular/router';
import {mergeMap} from 'rxjs/operators';

@Component({
  selector: 'app-general',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.css']
})
export class GeneralComponent implements OnInit {

  jobOffer: any;
  company: any;
  isLoading = true;

  constructor(
    private companyResourceService: CompanyResourceService,
    private jobOfferResourceService: JobOfferResourceService,
    private route: ActivatedRoute,
    private notifciationService: NotificationService
  ) {
  }

  ngOnInit() {
    this.route.parent.params.subscribe(params => {
      this.jobOfferResourceService.getOneJobOffer(+params['id']).pipe(
        mergeMap((jobOfferResponse: any) => {
          this.jobOffer = jobOfferResponse;
          return this.companyResourceService.getCompany(jobOfferResponse.companyId);
        })
      ).subscribe(
        (company) => this.company = company,
        (error) => {
          console.log(error);
        },
        () => {
          setTimeout(() => {
            this.isLoading = false;
          }, 250);
        }
      );
    });
  }
}

