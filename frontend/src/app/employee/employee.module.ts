import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {NavigationComponent} from './components/navigation/navigation.component';
import {SettingsComponent} from './components/settings/settings.component';
import {GeneralComponent} from './components/settings/general/general.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {LocationsComponent} from './components/settings/locations/locations.component';
import {StationsComponent} from './components/settings/stations/stations.component';
import {SettingsNavigationComponent} from './components/settings/settings-navigation/settings-navigation.component';
import {JobOffersComponent} from './components/job-offers/job-offers.component';
import {LoadingSpinnerComponent} from './components/shared/loading-spinner/loading-spinner.component';
import {ApplicationUnavailableComponent} from './components/shared/application-unavailable/application-unavailable.component';
import {NewJobOfferComponent} from './components/job-offers/new-job-offer/new-job-offer.component';
import {JobOfferOverviewComponent} from './components/job-offers/job-offer-overview/job-offer-overview.component';
import {IsAuthenticatedGuard} from '../shared/guard/is-authenticated.guard';
import {CustomHttpClient} from '../shared/service/custom-http-client';
import {UrlProvider} from '../shared/service/url-provider';
import {UserService} from '../shared/service/user.service';
import {HttpErrorInterceptor} from '../shared/class/http-error-interceptor';
import {EmployeeRouting} from './employee.routing';
import {EmployeeComponent} from './components/employee.component';
import {TokenService} from '../shared/service/auth/token.service';
import {EmployeesComponent} from './components/settings/employees/employees.component';


@NgModule({
  declarations: [
    EmployeeComponent,
    EmployeesComponent,
    DashboardComponent,
    NavigationComponent,
    SettingsComponent,
    GeneralComponent,
    LocationsComponent,
    StationsComponent,
    SettingsNavigationComponent,
    JobOffersComponent,
    LoadingSpinnerComponent,
    ApplicationUnavailableComponent,
    NewJobOfferComponent,
    JobOfferOverviewComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    EmployeeRouting
  ],
  providers: [
    IsAuthenticatedGuard,
    TokenService,
    CustomHttpClient,
    UrlProvider,
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    }
  ],
  exports: [
    EmployeeComponent,
    EmployeesComponent,
    DashboardComponent,
    NavigationComponent,
    SettingsComponent,
    GeneralComponent,
    LocationsComponent,
    StationsComponent,
    SettingsNavigationComponent,
    JobOffersComponent,
    LoadingSpinnerComponent,
    ApplicationUnavailableComponent,
    NewJobOfferComponent,
    JobOfferOverviewComponent,
    EmployeeRouting
  ],
  bootstrap: [
    EmployeeComponent
  ]
})

export class EmployeeModule { }
