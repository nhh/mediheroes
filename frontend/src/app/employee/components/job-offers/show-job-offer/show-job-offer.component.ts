import { Component, OnInit } from '@angular/core';
import {JobOfferRequest} from '../../../dtos/job-offer-request';
import {ActivatedRoute} from '@angular/router';
import {JobOfferResourceService} from '../../../../shared/service/resource/job-offer-resource.service';

@Component({
  selector: 'app-show-job-offer',
  templateUrl: './show-job-offer.component.html',
  styleUrls: ['./show-job-offer.component.css']
})
export class ShowJobOfferComponent implements OnInit {

  jobOfferRequest = new JobOfferRequest();
  isLoading = true;
  private jobOfferId : number;

  constructor(
    private jobOfferResourceService: JobOfferResourceService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.jobOfferId = +params['id'];
      this.loadCurrentJobOffer(this.jobOfferId);
    });
  }

  loadCurrentJobOffer(id : number){
    this.jobOfferResourceService.getJobOffer(id).subscribe(
      (data : any) => {
        this.jobOfferRequest = data;
      },
      (error) => {},
      () => {
        setTimeout(() => {
          this.isLoading = false
        }, 250)
      }
    );
  }

  updateJobOffer(){
    this.isLoading = true;
    this.jobOfferResourceService.updateJobOffer(this.jobOfferId, this.jobOfferRequest).subscribe(
      (data : any) => {
        this.jobOfferRequest = data;
      },
      (error) => {
      },
      () => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    );
  }

}
