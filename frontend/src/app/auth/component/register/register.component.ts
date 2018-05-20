import { Component, OnInit } from '@angular/core';
import {RegisterRequest} from '../../../shared/dto/register-request';
import {AuthService} from '../../../shared/service/auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerRequest = new RegisterRequest();

  isLoading = false;

  constructor(
    private authService : AuthService,
    private router : Router
  ) { }

  ngOnInit() {

  }

  register() {
    this.isLoading = true;
    return this.authService.register(this.registerRequest).subscribe(
      (user) => {
        this.router.navigate(["/login"])
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

}
