import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {DashboardComponent} from './component/dashboard/dashboard.component';
import {IsAuthenticatedGuard} from '../shared/guard/is-authenticated.guard';
import {UserService} from '../shared/service/user.service';
import {HttpErrorInterceptor} from '../shared/class/http-error-interceptor';
import {FreelancerRouting} from './freelancer.routing';
import {FreelancerComponent} from './component/freelancer.component';
import {NavigationComponent} from './component/navigation/navigation.component';
import {SettingsComponent} from './component/settings/settings.component';
import {SharedModule} from '../shared/shared.module';
import {IsFreelancerGuard} from '../shared/guard/is-freelancer.guard';
import { GeneralComponent } from './component/job-offers/general/general.component';
import { LocationsComponent } from './component/job-offers/locations/locations.component';
import { StationsComponent } from './component/job-offers/stations/stations.component';
import { ApplicationComponent } from './component/job-offers/application/application.component';
import { OverviewComponent } from './component/job-offers/overview/overview.component';
import { JobOfferComponent } from './component/job-offers/job-offer.component';
import { AddressComponent } from './component/settings/address/address.component';
import { BlacklistComponent } from './component/settings/blacklist/blacklist.component';
import { DocumentsComponent } from './component/settings/documents/documents.component';
import { SettingsGeneralComponent } from './component/settings/settings-general/settings-general.component';


@NgModule({
  declarations: [
    DashboardComponent,
    FreelancerComponent,
    NavigationComponent,
    SettingsComponent,
    GeneralComponent,
    LocationsComponent,
    StationsComponent,
    ApplicationComponent,
    OverviewComponent,
    JobOfferComponent,
    AddressComponent,
    BlacklistComponent,
    DocumentsComponent,
    SettingsGeneralComponent
  ],
  imports: [
    SharedModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    FreelancerRouting
  ],
  providers: [
    IsAuthenticatedGuard,
    IsFreelancerGuard,
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    }
  ],
  exports: [
    DashboardComponent,
    FreelancerComponent,
    FreelancerRouting,
    NavigationComponent
  ]
})

export class FreelancerModule { }
