import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivateChild, Router} from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../service/user.service';

@Injectable({
  providedIn: 'root'
})
export class IsEmployeeGuard implements CanActivate, CanActivateChild {

  constructor(private userService : UserService, private router : Router){}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if(this.userService.isEmployee()){
      return true;
    }

    if(this.userService.isOwner()){
      return true
    }

    this.router.navigate(['/freelancer/dashboard'])

  }

  canActivateChild(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if(this.userService.isEmployee()){
      return true;
    }

    if(this.userService.isOwner()){
      return true
    }

    this.router.navigate(['/freelancer/dashboard'])

  }
}
