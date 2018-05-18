import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../shared/service/auth/auth.service';

@Component({
  selector: 'employee-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(
    private authService : AuthService
  ) {}

  public navbar_toggle : boolean = false;

  public logout() : void {
    this.authService.logout();
  }

  ngOnInit() {}

}
