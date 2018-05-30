import { Component, OnInit } from '@angular/core';
import {JobOfferResourceService} from '../../../../shared/service/resource/job-offer-resource.service';
import {NotificationService} from '../../../../shared/service/notification.service';
import {Notification} from '../../../../shared/class/notification';

@Component({
  selector: 'app-job-offers',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  isLoading = false;
  jobOffers : any = [];

  constructor(
    private jobOfferResourceService : JobOfferResourceService,
    private notificationService : NotificationService
  ) { }

  ngOnInit() {
    return this.loadJobOffers();
  }

  loadJobOffers() {
    this.isLoading = true;
    this.jobOfferResourceService.getAllJobOffers().subscribe(
      data => this.jobOffers = data,
      error => console.log(error),
      () => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    );
  }

  notify(){
    this.notificationService.showToast(
      new Notification("Hello World","is-success","fa fa-heart fa-2x")
    )
  }

}
