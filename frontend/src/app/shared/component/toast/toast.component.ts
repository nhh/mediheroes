import { Component, OnInit } from '@angular/core';
import {Injectable} from '@angular/core';
import {Notification} from '../../class/notification';
import {NotificationService} from '../../service/notification.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.css']
})
@Injectable(
  {providedIn: 'root'}
)
export class ToastComponent implements OnInit {

  subscription : Subscription;
  notification : Notification;
  isActive = false;

  constructor(
    private notificationService : NotificationService
  ){
    this.subscription = this.notificationService
      .getToast()
      .subscribe(notification => this.setNotification(notification));
  }

  ngOnInit() {}

  private setNotification(notification : Notification){
    this.notification = notification;
    this.isActive = true;
    setTimeout(() => this.isActive = false, 3000);
  }

}
