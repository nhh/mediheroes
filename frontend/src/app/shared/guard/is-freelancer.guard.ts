import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';
import {CanActivateChild} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class IsFreelancerGuard implements CanActivate, CanActivateChild {

  constructor(
    private userService : UserService,
    private router : Router
  ){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if(this.userService.isFreelancer()){
      return true;
    }

    this.router.navigate(['/employee/dashboard']);

  }

  canActivateChild(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if(this.userService.isFreelancer()){
      return true;
    }

    this.router.navigate(['/employee/dashboard']);

  }

}
