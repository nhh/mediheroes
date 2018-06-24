import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {IsAuthenticatedGuard} from '../shared/guard/is-authenticated.guard';
import {UserService} from '../shared/service/user.service';
import {HttpErrorInterceptor} from '../shared/class/http-error-interceptor';
import {AdminRouting} from './admin.routing';
import { AdminComponent } from './component/admin.component';
import {SharedModule} from '../shared/shared.module';
import { NavigationComponent } from './component/navigation/navigation.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { UsersComponent } from './component/users/users.component';
import { CompaniesComponent } from './component/companies/companies.component';
import { JobOffersComponent } from './component/job-offers/job-offers.component';
import { SettingsComponent } from './component/settings/settings.component';
import {MetricResourceService} from '../shared/service/resource/metric-resource.service';


@NgModule({
  declarations: [
    AdminComponent,
    NavigationComponent,
    DashboardComponent,
    UsersComponent,
    CompaniesComponent,
    JobOffersComponent,
    SettingsComponent
  ],
  imports: [
    SharedModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AdminRouting
  ],
  providers: [
    IsAuthenticatedGuard,
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    },
    MetricResourceService
  ],
  exports: [
    AdminRouting
  ],
  bootstrap: [
    AdminComponent
  ]
})

export class AdminModule { }
