import { Injectable } from '@angular/core';
import {AbstractResourceService} from './abstract-resource.service';

@Injectable({
  providedIn: 'root'
})
export class UserResourceService extends AbstractResourceService {

  private basePath = "/api/v1/users";

  getUser(){
    return this.http.get(this.basePath, this.authenticatedHttpOptions())
  }

  getCurrentUser(){
    return this.http.get(this.basePath + '/me', this.authenticatedHttpOptions())
  }

}
