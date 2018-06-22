import { Component, OnInit } from '@angular/core';
import {TokenResourceService} from '../../../shared/service/resource/token-resource.service';
import {AuthService} from '../../../shared/service/auth/auth.service';
import {LoginRequest} from '../../../shared/dto/login-request';
import {Router} from '@angular/router';
import {NotificationService} from '../../../shared/service/notification.service';
import {Notification} from '../../../shared/class/notification';

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
    private router : Router,
    private notificationService : NotificationService
  ) { }

  onSubmit() {
    this.isLoading = true;
    return this.authService.authenticate(this.loginRequest).subscribe(
      (currentUser) => {

        this.notificationService.showToast(new Notification(
          "Willkommen zurück!",
          "is-success",
          "fa fa-heart fa-2x"));

        if(currentUser.roles.includes("OWNER")){
          return this.router.navigate(['/employee/dashboard']);
        } else if (currentUser.roles.includes("EMPLOYEE")) {
          return this.router.navigate(['/employee/dashboard']);
        } else if (currentUser.roles.includes("ADMIN")) {
          return this.router.navigate(['/admin/dashboard']);
        } else {
          return this.router.navigate(['/freelancer/dashboard']);
        }

      },
      (error) => {
        this.notificationService.showToast(new Notification(
          "Leider ist ein Fehler aufgetreten, probieren Sie es erneut",
          "is-danger",
          "fa fa-times fa-2x"));
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
