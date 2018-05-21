import { Injectable } from '@angular/core';
import { LoginRequest } from '../../dto/login-request';
import {TokenResourceService} from '../resource/token-resource.service';
import {mergeMap} from 'rxjs/operators';
import {UserResourceService} from '../resource/user-resource.service';
import {RegisterRequest} from '../../dto/register-request';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private tokenResourceService : TokenResourceService,
    private userResourceService : UserResourceService,
    private router : Router
  ) { }

  public logout() {
    localStorage.clear();
    return this.tokenResourceService.deleteToken().subscribe(
      (data) => this.router.navigate(['/login']),
      (error) => this.router.navigate(['/login'])
    );
  }

  public isAuthenticated() {
    let currentUser = localStorage.getItem('currentUser');
    let authToken = localStorage.getItem('x-auth-token');
    return authToken != null && currentUser != null;
  }

  public authenticate(loginRequest : LoginRequest) {

    return this.tokenResourceService.createToken(loginRequest).pipe(
      mergeMap((data : any) => {
        localStorage.setItem('x-auth-token', data.token);
        return this.userResourceService.getCurrentUser();
      })
    ).pipe(
      mergeMap(
        (currentUser : any) => {
          localStorage.setItem('currentUser', JSON.stringify(currentUser));
          return Promise.resolve(currentUser);
        }
      )
    );
  }

  public register(registerRequest : RegisterRequest){
    return this.userResourceService.createUser(registerRequest);
  }

}
