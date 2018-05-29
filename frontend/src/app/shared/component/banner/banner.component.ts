import { Component, OnInit } from '@angular/core';
import {Notification} from '../../class/notification';
import {Injectable} from '@angular/core';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
@Injectable()
export class BannerComponent implements OnInit {

  banners : Notification[] = [];
  deleteInterval = setInterval(() => this.removeNotification(), 3000);

  constructor() { }

  ngOnInit() {

  }

  addNotification(notification : Notification) {
    this.banners.push(notification);
    this.deleteInterval = setInterval(() => this.removeNotification(), 3000);
  }

  removeNotification(){

    if(this.banners.length == 0){
      clearInterval(this.deleteInterval);
      return;
    }

    this.banners.pop();
  }

}
