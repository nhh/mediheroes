import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../shared/service/auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'freelancer-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(
    private authService : AuthService,
    private router : Router
  ) { }

  ngOnInit() {
  }

  logout(){
    this.authService.logout();
    this.router.navigate(["/login"])
  }

}
