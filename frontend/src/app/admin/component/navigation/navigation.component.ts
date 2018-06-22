import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../shared/service/auth/auth.service';

@Component({
  selector: 'admin-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit() {

  }

  logout() {
    return this.authService.logout();
  }

}
