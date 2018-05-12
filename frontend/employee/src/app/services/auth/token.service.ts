import { Injectable } from '@angular/core';
import {LoginCredentials} from "../../dtos/login-credentials";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UrlProvider} from "../url-provider";
import {mergeMap} from "rxjs/operators"

import {Router} from "@angular/router";

@Injectable()
export class TokenService {

  constructor(private http: HttpClient, private urlProvider : UrlProvider, private router : Router) { }

  public authenticate(loginCredentials : LoginCredentials) {

    const initialHttpOptions : any = {
      headers: new HttpHeaders({
        'X-Requested-With': 'XMLHttpRequest',
        "Authorization": "Basic " + btoa(loginCredentials.email + ":" + loginCredentials.password)
      }),
      withCredentials: true
    };

    return this.http.get(this.urlProvider.authTokenResource(), initialHttpOptions).pipe(
      mergeMap((data : any) => {
        localStorage.setItem("X-AUTH-TOKEN", data.token);
        return this.http.get(this.urlProvider.currentUserResource(), { headers: this.authenthicatedHttpHeaders()})
      })
    ).subscribe((currentUser) => {
      localStorage.setItem("currentUser", JSON.stringify(currentUser));
      return this.router.navigate(["/employee/dashboard"]);
    })

  }

  public getAuthenticationToken() : string {
    return localStorage.getItem('X-AUTH-TOKEN');
  }

  public authenthicatedHttpHeaders() : HttpHeaders {
    return new HttpHeaders({
      'X-Requested-With': 'XMLHttpRequest',
      "X-AUTH-TOKEN": this.getAuthenticationToken()
    })
  }

  public isAuthenticated() {
    let currentUser = localStorage.getItem("currentUser");
    let authToken = localStorage.getItem("X-AUTH-TOKEN");
    return authToken != null && currentUser != null
  }

  public logout() {
      this.http.delete(this.urlProvider.authTokenResource(), {headers: this.authenthicatedHttpHeaders()}).subscribe();
      localStorage.removeItem('X-AUTH-TOKEN');
      localStorage.removeItem('currentUser');
      return this.router.navigate(['/login']);
  }

}
