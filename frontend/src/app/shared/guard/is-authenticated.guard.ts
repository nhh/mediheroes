import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivateChild} from '@angular/router';
import { Router} from "@angular/router";
import {Observable} from 'rxjs';
import {AuthService} from '../service/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class IsAuthenticatedGuard implements CanActivate, CanActivateChild {

  constructor(
    private authService: AuthService,
    private router : Router
  ) {};

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if(this.authService.isAuthenticated()){
      return true
    } else {
      this.authService.logout();
      this.router.navigate(['/login']);
      return false
    }
  }

  canActivateChild(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if(this.authService.isAuthenticated()){
      return true
    } else {
      this.authService.logout();
      this.router.navigate(['/login']);
      return false
    }
  }

}
