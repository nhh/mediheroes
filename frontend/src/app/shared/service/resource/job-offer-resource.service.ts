import { Injectable } from '@angular/core';
import {AbstractResourceService} from './abstract-resource.service';
import {JobOfferRequest} from '../../../employee/dtos/job-offer-request';

@Injectable({
  providedIn: 'root'
})
export class JobOfferResourceService extends AbstractResourceService {

  private basePath = "/api/v1/job-offers";

  getJobOffers(companyId: number){
    return this.http.get(this.basePath + '?companyId=' + companyId, this.authenticatedHttpOptions());
  }

  deleteJobOffer(id : number){
    return this.http.delete(this.basePath + '/' + id, this.authenticatedHttpOptions());
  }

  createJobOffer(jobOfferRequest : JobOfferRequest){
    return this.http.post(this.basePath, jobOfferRequest, this.authenticatedHttpOptions());
  }

  getJobOffer(id : number){
    return this.http.get(this.basePath + '/' + id, this.authenticatedHttpOptions());
  }

  updateJobOffer(id : number, jobOffer : JobOfferRequest){
    return this.http.put(this.basePath + '/' + id, jobOffer, this.authenticatedHttpOptions());
  }


}
