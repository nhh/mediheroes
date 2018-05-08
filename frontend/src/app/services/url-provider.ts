import {Injectable} from '@angular/core';
import {UserService} from "./auth/user.service";

@Injectable()
export class UrlProvider {

  private baseUrl : string = "http://localhost:8080/api/v1";

  constructor(private userService : UserService) {}

  userResource(){
    return this.baseUrl + "/users"
  }

  companyResource(){
    return this.baseUrl + "/companies"
  }

  locationResource(){
    return this.baseUrl + "/locations"
  }

  authResource(){
    return this.baseUrl + "/auth"
  }

  authTokenResource(){
    return this.baseUrl + "/auth/token"
  }

  currentUserResource(){
    return this.baseUrl + "/auth/me"
  }

  currentCompanyResource(){
    return this.baseUrl + "/companies/" + this.userService.getCurrentCompany().id
  }


}
