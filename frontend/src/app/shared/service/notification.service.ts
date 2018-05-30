import { Injectable } from '@angular/core';
import { Notification } from '../class/notification';
import { Subject } from 'rxjs';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private subject = new Subject<Notification>();

  constructor() {

  }

  getToast(): Observable<any> {
    return this.subject.asObservable();
  }

  showToast(notification : Notification) {
    this.subject.next(notification);
  }

}
