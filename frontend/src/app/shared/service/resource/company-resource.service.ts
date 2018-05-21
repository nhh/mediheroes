import { Injectable } from '@angular/core';
import {AbstractResourceService} from './abstract-resource.service';
import {CompanyRequest} from '../../dto/company-request';
import {JobOfferRequest} from '../../../employee/dtos/job-offer-request';

@Injectable({
  providedIn: 'root'
})
export class CompanyResourceService extends AbstractResourceService {

  private basePath = "/api/v1/companies";

  getAllCompanies() {
    return this.http.get(this.basePath, this.authenticatedHttpOptions())
  }

  getCompany(companyId: number) {
    return this.http.get(this.basePath+ '/' + companyId, this.authenticatedHttpOptions())
  }

  createCompany(companyRequest : CompanyRequest){

  }

  getLocations(companyId : number) {
    return this.http.get(this.basePath + '/' + companyId + '/locations', this.authenticatedHttpOptions());
  }

  getEmployees(companyId : number) {
    return this.http.get(this.basePath + '/' + companyId + '/employees', this.authenticatedHttpOptions());
  }

  addEmployee(companyId : number, email : string) {
    return this.http.post(this.basePath + '/' + companyId + '/employees', email, this.authenticatedHttpOptions());
  }

  removeEmployee(companyId : number, employeeId : number) {
    return this.http.delete(this.basePath + '/' + companyId + '/employees' + '/' + employeeId, this.authenticatedHttpOptions());
  }

  getOneJobOffer(companyId : number, jobOfferId : number){
    return this.http.get(this.basePath + '/' + companyId + '/job-offers/' + jobOfferId, this.authenticatedHttpOptions());
  }

  getJobOffers(companyId : number){
    return this.http.get(this.basePath + '/' + companyId + '/job-offers', this.authenticatedHttpOptions());
  }

  addJobOffer(companyId : number, jobOfferRequest : JobOfferRequest) {
    return this.http.post(this.basePath + '/' + companyId + '/job-offers', jobOfferRequest, this.authenticatedHttpOptions());
  }

  removeJobOffer(companyId : number, jobOfferId : number) {
    return this.http.delete(this.basePath + '/' + companyId + '/job-offers/' + jobOfferId, this.authenticatedHttpOptions());
  }

  updateJobOffer(companyId : number, jobOfferId : number, jobOffer : JobOfferRequest) {
    return this.http.put(this.basePath + '/' + companyId + '/job-offers/' + jobOfferId, jobOffer, this.authenticatedHttpOptions());
  }

}
