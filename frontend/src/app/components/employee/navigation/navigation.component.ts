import { Component, OnInit } from '@angular/core';
import { TokenService} from "../../../services/auth/token.service";
import { Router} from "@angular/router";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(
    private tokenService : TokenService
  ) {}

  public navbar_toggle : boolean = false;

  public logout() : void {
    this.tokenService.logout();
  }

  ngOnInit() {}

}
