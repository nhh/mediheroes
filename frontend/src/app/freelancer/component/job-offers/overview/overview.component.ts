import { Component, OnInit } from '@angular/core';
import {NotificationService} from '../../../../shared/service/notification.service';
import {Notification} from '../../../../shared/class/notification';
import {JobOfferResourceService} from '../../../../shared/service/resource/job-offer-resource.service';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css']
})
export class OverviewComponent implements OnInit {

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
