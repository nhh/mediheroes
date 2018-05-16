import {Injectable} from '@angular/core';
import {UserService} from "./user.service";

@Injectable()
export class UrlProvider {

  private baseUrl : string = "/api/v1";

  constructor(private userService : UserService) {}

  jobOfferResource() {
    return this.baseUrl + "/job-offers"
  }

  userResource() {
    return this.baseUrl + "/users"
  }

  companyResource() {
    return this.baseUrl + "/companies"
  }

  locationResource() {
    return this.baseUrl + "/locations"
  }

  authResource() {
    return this.baseUrl + "/auth"
  }

  authTokenResource() {
    return this.baseUrl + "/auth/token"
  }

  currentUserResource() {
    return this.baseUrl + "/auth/me"
  }

  currentCompanyResource() {
    return this.baseUrl + "/companies/" + this.userService.getCurrentCompany().id
  }
  currentCompanyEmployeesResource(){
    return this.baseUrl + "/companies/" + this.userService.getCurrentCompany().id + '/employees'
  }


}
