import { Component, OnInit } from '@angular/core';
import {JobOfferRequest} from '../../../dtos/job-offer-request';
import {CustomHttpClient} from '../../../../shared/service/custom-http-client';
import {UrlProvider} from '../../../../shared/service/url-provider';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-show-job-offer',
  templateUrl: './show-job-offer.component.html',
  styleUrls: ['./show-job-offer.component.css']
})
export class ShowJobOfferComponent implements OnInit {

  private jobOfferRequest = new JobOfferRequest();
  private isLoading = true;
  private jobOfferId : number;

  constructor(
    private http: CustomHttpClient,
    private urlProvider : UrlProvider,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.jobOfferId = +params['id'];
      this.loadCurrentJobOffer(this.jobOfferId);
    });
  }

  loadCurrentJobOffer(id : number){
    this.http.get(this.urlProvider.jobOfferResource() + '/' + id).subscribe(
      (data : JobOfferRequest) => {
        this.jobOfferRequest = data;
        console.log(this.jobOfferRequest);
      },
      (error) => {},
      () => {
        setTimeout(() => {
          this.isLoading = false
        }, 250)
      }
    )
  }

  updateJobOffer(jobOffer : JobOfferRequest){
    this.isLoading = true;
    this.http.put(this.urlProvider.jobOfferResource() + '/' + this.jobOfferId, jobOffer).subscribe(
      (data : JobOfferRequest) => {
        // Show notification
        this.jobOfferRequest = data;
      },
      (error) => {
        // Throw error
      },
      () => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    )
  }

}
