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
    private tokenService : TokenService,
    private router : Router
  ) {}

  public navbar_toggle : boolean = false;

  logout(){
    this.tokenService.logout();
  }

  ngOnInit() {}

}
