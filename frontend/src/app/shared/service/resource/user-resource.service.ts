import { Injectable } from '@angular/core';
import {AbstractResourceService} from './abstract-resource.service';
import {RegisterRequest} from '../../dto/register-request';
import {ProfileRequest} from '../../dto/user/profile-request';
import {AddressRequest} from '../../dto/user/address-request';

@Injectable({
  providedIn: 'root'
})
export class UserResourceService extends AbstractResourceService {

  private basePath = "/api/v1/users";

  downloadFile(userId: number, documentId: string) {
    const options: any = {
      headers: this.authenticatedHttpHeaders(),
      responseType: "blob",
      withCredentials: true
    };
    return this.http.get(this.basePath + '/' + userId + "/files/" + documentId, options)
  }

  getUser(){
    return this.http.get(this.basePath, this.authenticatedHttpOptions())
  }

  getCurrentUser(){
    return this.http.get(this.basePath + '/me', this.authenticatedHttpOptions())
  }

  createUser(registerRequest : RegisterRequest){
    return this.http.post(this.basePath, registerRequest)
  }

  updateMyProfile(userId: number, profileRequest : ProfileRequest) {
    return this.http.put(this.basePath + '/' + userId + '/profile', profileRequest, this.authenticatedHttpOptions())
  }

  updateMyAddress(userId: number, addressRequest : AddressRequest) {
    return this.http.put(this.basePath + userId + '/address', addressRequest, this.authenticatedHttpOptions())
  }

}
