import { Component, OnInit } from '@angular/core';
import {JobOfferApplicationRequest} from '../../../../shared/dto/job-offer-application-request';
import {Notification} from '../../../../shared/class/notification';
import {JobOfferResourceService} from '../../../../shared/service/resource/job-offer-resource.service';
import {ActivatedRoute} from '@angular/router';
import {NotificationService} from '../../../../shared/service/notification.service';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css']
})
export class ApplicationComponent implements OnInit {

  jobOfferApplicationRequest = new JobOfferApplicationRequest();
  jobOfferId: number;

  constructor(
    private jobOfferResourceService: JobOfferResourceService,
    private notificationService: NotificationService,
    private route: ActivatedRoute
  ) {
    this.route.parent.params.subscribe(params => this.jobOfferId = params.id);
  }
  ngOnInit() {

  }

  createJobOfferApplication() {
    this.jobOfferResourceService.createJobOfferApplication(this.jobOfferId, this.jobOfferApplicationRequest).subscribe(
      success => {
        this.notificationService.showToast(new Notification('Bewerbung erfolgreich erstellt!', 'is-success', 'fa fa-2x fa-check-circle-o'));
        this.jobOfferApplicationRequest = new JobOfferApplicationRequest();
      },
      error => {
        console.log(error);
      },
    );
  }

}
