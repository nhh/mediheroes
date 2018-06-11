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
import {IndexComponent} from './component/job-offers/index/index.component';
import {SharedModule} from '../shared/shared.module';
import {IsFreelancerGuard} from '../shared/guard/is-freelancer.guard';
import {ShowComponent} from './component/job-offers/show/show.component';
import { ProfileComponent } from './component/settings/profile/profile.component';
import { SettingsNavigationComponent } from './component/settings/settings-navigation/settings-navigation.component';


@NgModule({
  declarations: [
    DashboardComponent,
    FreelancerComponent,
    NavigationComponent,
    SettingsComponent,
    IndexComponent,
    ShowComponent,
    ProfileComponent,
    SettingsNavigationComponent
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
