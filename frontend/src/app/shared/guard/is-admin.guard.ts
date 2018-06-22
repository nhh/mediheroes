import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';
import {CanActivateChild} from '@angular/router';
import {AuthService} from '../service/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class IsAdminGuard implements CanActivate, CanActivateChild {

  constructor(
    private userService : UserService,
    private router : Router,
    private authService: AuthService
  ){}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if(this.userService.isAdmin()){
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

    if (this.userService.isAdmin()){
      return true;
    }

    this.router.navigate(['/admin/dashboard']);
  }

}
