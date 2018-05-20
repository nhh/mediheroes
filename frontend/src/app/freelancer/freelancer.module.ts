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
import { SettingsComponent } from './component/settings/settings.component';
import { JobOffersComponent } from './component/job-offers/job-offers.component';


@NgModule({
  declarations: [
    DashboardComponent,
    FreelancerComponent,
    NavigationComponent,
    SettingsComponent,
    JobOffersComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    FreelancerRouting
  ],
  providers: [
    IsAuthenticatedGuard,
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
