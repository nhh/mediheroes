import { Component, OnInit } from '@angular/core';
import {JobOfferResourceService} from '../../../../shared/service/resource/job-offer-resource.service';

@Component({
  selector: 'app-job-offers',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  isLoading = false;
  jobOffers : any = [];

  constructor(
    private jobOfferResourceService : JobOfferResourceService
  ) { }

  ngOnInit() {
    return this.loadJobOffers();
  }

  loadJobOffers() {
    this.isLoading = true;
    this.jobOfferResourceService.getAllJobOffers().subscribe(
      data => this.jobOffers = data,
      error => console.log(error),
      () => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    );
  }

}
