import { Injectable } from '@angular/core';
import {AbstractResourceService} from './abstract-resource.service';
import {JobOfferRequest} from '../../../employee/dtos/job-offer-request';
import {JobOfferApplicationRequest} from '../../dto/job-offer-application-request';

@Injectable({
  providedIn: 'root'
})
export class JobOfferResourceService extends AbstractResourceService {

  private basePath = "/api/v1/job-offers";


  getAllJobOffers(){
    return this.http.get(this.basePath, this.authenticatedHttpOptions());
  }

  getOneJobOffer(id : number){
    return this.http.get(this.basePath + '/' + id, this.authenticatedHttpOptions());
  }

  public createJobOfferApplication(
    jobOfferId: number, jobOfferApplication: JobOfferApplicationRequest
  ){
    return this.http.post(this.basePath + '/' + jobOfferId + '/applications', jobOfferApplication, this.authenticatedHttpOptions());

  }

}
