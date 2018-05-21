import { Component, OnInit } from '@angular/core';
import {JobOfferRequest} from '../../../dtos/job-offer-request';
import {ActivatedRoute} from '@angular/router';
import {CompanyResourceService} from '../../../../shared/service/resource/company-resource.service';
import {UserService} from '../../../../shared/service/user.service';

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
    private companyResourceService: CompanyResourceService,
    private userService : UserService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.jobOfferId = +params['id'];
      this.loadCurrentJobOffer(this.jobOfferId);
    });
  }

  loadCurrentJobOffer(jobOfferId : number){
    this.companyResourceService.getOneJobOffer(this.userService.getCurrentCompany().id, jobOfferId).subscribe(
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
    this.companyResourceService.updateJobOffer(this.userService.getCurrentCompany().id, this.jobOfferId, this.jobOfferRequest).subscribe(
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
