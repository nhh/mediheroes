import { Component, OnInit } from '@angular/core';
import {ProfileRequest} from '../../../../shared/dto/user/profile-request';
import {UserService} from '../../../../shared/service/user.service';
import {UserResourceService} from '../../../../shared/service/resource/user-resource.service';
import {Notification} from '../../../../shared/class/notification';
import {NotificationService} from '../../../../shared/service/notification.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profileRequest = new ProfileRequest();
  isLoading = false;

  constructor(
    private userService: UserService,
    private userResourceService: UserResourceService,
    private notificationService: NotificationService
  ) {
    this.profileRequest.email = userService.getCurrentUser().email;
    this.profileRequest.firstname = userService.getCurrentUser().firstname;
    this.profileRequest.lastname = userService.getCurrentUser().lastname;
  }

  ngOnInit() {

  }

  updateMyProfile(){
    this.isLoading = true;
    this.userResourceService.updateMyProfile(this.userService.getCurrentUser().id, this.profileRequest).subscribe(
      (data) => {
        setTimeout(() => {
          this.notificationService.showToast(new Notification("Ihr Profil wurde aktualisiert!","is-success","fa fa-2x fa-user"));
          this.isLoading = false;
        }, 250);
      },
      (error) => {},
      () => {}
    )
  }

}
