import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivateChild} from '@angular/router';
import {TokenService} from "../services/auth/token.service";
import { Router} from "@angular/router";
import {Observable} from 'rxjs';

@Injectable()
export class IsAuthenticatedGuard implements CanActivate, CanActivateChild {

  constructor(private tokenService: TokenService, private router : Router) {};

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if(this.tokenService.isAuthenticated()){
      return true
    } else {
      this.router.navigate(['/login']);
      return false
    }
  }

  canActivateChild(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if(this.tokenService.isAuthenticated()){
      return true
    } else {
      this.router.navigate(['/login']);
      return false
    }
  }

}
