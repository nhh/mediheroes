import { Component, OnInit } from '@angular/core';
import { LoginCredentials } from "../../../dtos/login-credentials";
import { TokenService} from "../../../services/auth/token.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  providers: [ TokenService ],
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginCredentials = new LoginCredentials("", "");

  submitted = false;

  onSubmit() {
    this.tokenService.authenticate(this.loginCredentials)
  }


  constructor(private tokenService : TokenService) { }

  ngOnInit() {

  }

}