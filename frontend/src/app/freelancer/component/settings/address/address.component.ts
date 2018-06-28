import { Component, OnInit } from '@angular/core';
import {AddressRequest} from '../../../../shared/dto/user/address-request';
import {UserResourceService} from '../../../../shared/service/resource/user-resource.service';
import {UserService} from '../../../../shared/service/user.service';
import {NotificationService} from '../../../../shared/service/notification.service';
import {Notification} from '../../../../shared/class/notification';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  addressRequest = new AddressRequest();
  isLoading = false;

  constructor(
    private userService: UserService,
    private userResourceService: UserResourceService,
    private notificationService: NotificationService
  ) {
  }

  ngOnInit() {

  }

  updateMyAddress() {
    this.userResourceService.updateMyAddress(this.userService.getCurrentUser().id, this.addressRequest).subscribe(
      (data) => {
        this.notificationService.showToast(new Notification("Ihr Profil wurde aktualisiert!","is-success","fa fa-2x fa-user"))
      },
      (error) => {},
      () => {}
    )
  }

  getActualAddress(){
    this.isLoading = true;
  }

}
