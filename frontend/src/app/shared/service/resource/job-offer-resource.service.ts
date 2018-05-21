import { Injectable } from '@angular/core';
import {AbstractResourceService} from './abstract-resource.service';
import {JobOfferRequest} from '../../../employee/dtos/job-offer-request';

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


}
