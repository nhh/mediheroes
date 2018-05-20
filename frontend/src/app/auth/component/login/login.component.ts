import { Component, OnInit } from '@angular/core';
import {TokenResourceService} from '../../../shared/service/resource/token-resource.service';
import {AuthService} from '../../../shared/service/auth/auth.service';
import {LoginRequest} from '../../../shared/dto/login-request';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequest = new LoginRequest();
  isLoading = false;

  constructor(
    private tokenResourceService : TokenResourceService,
    private authService : AuthService,
    private router : Router
  ) { }

  onSubmit() {
    this.isLoading = true;
    return this.authService.authenticate(this.loginRequest).subscribe(
      (currentUser) => {

        if(currentUser.roles.includes("OWNER")){
          return this.router.navigate(['/employee/dashboard']);
        } else if (currentUser.roles.includes("EMPLOYEE")) {
          return this.router.navigate(['/employee/dashboard']);
        } else {
          return this.router.navigate(['/freelancer/dashboard']);
        }

      },
      (error) => {
        // Todo show notification
        this.isLoading = false;
        console.log(error);
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit() {

  }

}
