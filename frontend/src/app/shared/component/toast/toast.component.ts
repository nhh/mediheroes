import { Component, OnInit } from '@angular/core';
import {BannerComponent} from '../banner/banner.component';
import {Notification} from '../../class/notification';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.css']
})
export class ToastComponent extends BannerComponent implements OnInit {

  ngOnInit() {
    this.banners.push(
      new Notification(
        "Wow, Welldone!",
        "is-success",
        "fa fa-check-circle-o fa-2x"
      )
    );
  }

}
