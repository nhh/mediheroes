import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TokenService} from "./auth/token.service";

@Injectable()
export class CustomHttpClient {

  constructor(private http: HttpClient, private tokenService : TokenService) {}

  get(url) {
    let headers = this.tokenService.authenthicatedHttpHeaders();
    return this.http.get(url, {
      headers: headers
    });
  }

  post(url, data) {
    let headers = this.tokenService.authenthicatedHttpHeaders();
    this.createAuthorizationHeader(headers);
    return this.http.post(url, data, {
      headers: headers
    });
  }

  delete(url){
    let headers = this.tokenService.authenthicatedHttpHeaders();
    return this.http.delete(url, {
      headers: headers
    });
  }

  post(url, data) {
    let headers = this.tokenService.authenthicatedHttpHeaders();
    return this.http.put(url, data, {
      headers: headers
    });
  }

}
