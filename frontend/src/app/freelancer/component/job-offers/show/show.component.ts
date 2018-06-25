import { Component, OnInit } from '@angular/core';
import {JobOfferResourceService} from '../../../../shared/service/resource/job-offer-resource.service';
import {ActivatedRoute} from '@angular/router';
import {CompanyResourceService} from '../../../../shared/service/resource/company-resource.service';
import {mergeMap} from 'rxjs/operators';
import {JobOfferApplicationRequest} from '../../../../shared/dto/job-offer-application-request';
import {NotificationService} from '../../../../shared/service/notification.service';
import {Notification} from '../../../../shared/class/notification';

@Component({
  selector: 'app-show',
  templateUrl: './show.component.html',
  styleUrls: ['./show.component.css']
})
export class ShowComponent implements OnInit {

  jobOffer : any;
  company : any;
  isLoading = true;
  jobOfferApplicationRequest = new JobOfferApplicationRequest();
  showModalDialog = false;

  constructor(
    private companyResourceService : CompanyResourceService,
    private jobOfferResourceService : JobOfferResourceService,
    private route: ActivatedRoute,
    private notifciationService: NotificationService
  ) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.jobOfferResourceService.getOneJobOffer(+params['id']).pipe(
        mergeMap((jobOfferResponse : any) => {
          this.jobOffer = jobOfferResponse;
          return this.companyResourceService.getCompany(jobOfferResponse.companyId);
        })
      ).subscribe(
        (company) => this.company = company,
        (error) => {},
        () => {
          setTimeout(() => {this.isLoading = false}, 250)
        }
      )
    });
  }

  createJobOfferApplication() {
    this.jobOfferResourceService.createJobOfferApplication(this.jobOffer.id, this.jobOfferApplicationRequest).subscribe(
      success => {
        this.showModalDialog = false;
        this.notifciationService.showToast(new Notification("Bewerbung erfolgreich erstellt!", "is-success", "fa fa-success"))
        this.jobOfferApplicationRequest = new JobOfferApplicationRequest();
      },
      error => {console.log(error)},
    )
  }
}
