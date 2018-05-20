import { Injectable } from '@angular/core';
import {AbstractResourceService} from './abstract-resource.service';
import {RegisterRequest} from '../../dto/register-request';

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

  createUser(registerRequest : RegisterRequest){
    return this.http.post(this.basePath, registerRequest)
  }

}
