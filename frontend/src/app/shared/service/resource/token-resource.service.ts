import { Injectable } from '@angular/core';
import {HttpHeaders} from '@angular/common/http';
import {LoginRequest} from '../../dto/login-request';
import {AbstractResourceService} from './abstract-resource.service';

@Injectable({
  providedIn: 'root'
})
export class TokenResourceService extends AbstractResourceService {

  private basePath = "/api/v1/auth";

  createToken(loginRequest : LoginRequest){
    const options : any = {
      headers: new HttpHeaders({
        'X-Requested-With': 'XMLHttpRequest',
        'Authorization': 'Basic ' + btoa(loginRequest.email + ':' + loginRequest.password)
      }),
      withCredentials: true
    };
    return this.http.get(this.basePath + '/token', options);
  }

  getToken(){

  }

  deleteToken(){
    return this.http.delete(this.basePath + '/auth/token', this.authenticatedHttpOptions())
  }

}
