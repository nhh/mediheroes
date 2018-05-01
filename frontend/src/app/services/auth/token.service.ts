import { Injectable } from '@angular/core';
import {LoginCredentials} from "../../classes/login-credentials";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable()
export class TokenService {

  private apiUrl : string = "http://localhost:8080";
  private tokenResourcePath : string = "/api/v1/auth/token";

  public authenticate(loginCredentials : LoginCredentials) {

    const initialHttpOptions : any = {
      headers: new HttpHeaders({
        'X-Requested-With': 'XMLHttpRequest',
        "Authorization": "Basic " + btoa(loginCredentials.email + ":" + loginCredentials.password)
      }),
      withCredentials: true
    };

    return this.http.get(this.apiUrl + this.tokenResourcePath, initialHttpOptions)
      .subscribe(
        response => this.setTokenAndGotoDashboard(response),
        err => console.log("User authentication failed!")
      )
  }

  private setTokenAndGotoDashboard(token: any) {
    localStorage.setItem('x-auth-token', token.token);
    this.router.navigate(['/employee/dashboard']);
  }

  private getAuthenticationToken() : string {
    return localStorage.getItem('x-auth-token');
  }

  private authenthicatedHttpHeaders() : HttpHeaders {
    return new HttpHeaders({
      'X-Requested-With': 'XMLHttpRequest',
      "X-Auth-Token": this.getAuthenticationToken()
    })
  }

  public authenticatedHttpOptions() : any {
    return {
      headers: this.authenthicatedHttpHeaders(),
      withCredentials: true
    }
  }

  public logout() {
      this.http.delete(this.apiUrl + this.tokenResourcePath, this.authenticatedHttpOptions()).subscribe();
      localStorage.removeItem('x-auth-token');
      return this.router.navigate(['/login']);
  }

  public isAuthenthicated() : boolean {
    return localStorage.getItem("x-auth-token") !== null
  }

  constructor(private http: HttpClient, private router : Router) { }

}
