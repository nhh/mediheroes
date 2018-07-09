import { Component, OnInit } from '@angular/core';
import {ProfileRequest} from '../../../../shared/dto/user/profile-request';
import {UserService} from '../../../../shared/service/user.service';
import {UserResourceService} from '../../../../shared/service/resource/user-resource.service';
import {Notification} from '../../../../shared/class/notification';
import {NotificationService} from '../../../../shared/service/notification.service';
import {DomSanitizer} from '@angular/platform-browser';
import {SafeUrl} from '@angular/platform-browser';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profileRequest = new ProfileRequest();
  profileImageUrl: SafeUrl = "";
  isLoading = false;

  constructor(
    private userService: UserService,
    private userResourceService: UserResourceService,
    private notificationService: NotificationService,
    private domSanitizer: DomSanitizer
  ) {
    this.profileRequest.email = userService.getCurrentUser().email;
    this.profileRequest.firstname = userService.getCurrentUser().firstname;
    this.profileRequest.lastname = userService.getCurrentUser().lastname;
  }

  ngOnInit() {
    this.loadProfileImage();
  }

  loadProfileImage() {
    const imageId = this.userService.getCurrentUser().imageId;

    if(imageId == null){
      return setTimeout(() => {this.isLoading = false}, 250);
    }

    this.isLoading = true;
    this.userResourceService.downloadFile(this.userService.getCurrentUser().id, this.userService.getCurrentUser().imageId).subscribe(
      (data) => {
        this.profileImageUrl = this.domSanitizer.bypassSecurityTrustUrl(URL.createObjectURL(data));
        setTimeout(() => {this.isLoading = false}, 250);
      },
      (error) => {
        console.log(error);
        setTimeout(() => {this.isLoading = false}, 250);
      },
    () => {}
    );
  }

  updateMyProfile() {
    this.isLoading = true;
    this.userResourceService.updateMyProfile(this.userService.getCurrentUser().id, this.profileRequest).subscribe(
      (data) => {
        setTimeout(() => {
          this.isLoading = false;
          this.notificationService.showToast(new Notification('Ihr Profil wurde aktualisiert!','is-success','fa fa-2x fa-user'));
        }, 250);
      },
      (error) => {},
      () => {}
    );
  }

}
