import { Component, OnInit } from '@angular/core';
import { TokenService} from "../../../shared/service/auth/token.service";

@Component({
  selector: 'employee-navigation',
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
