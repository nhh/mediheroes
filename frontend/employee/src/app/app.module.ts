import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRouting} from "./app.routing";
import { AppComponent } from './components/app.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { FreelancerComponent } from './components/freelancer/freelancer/freelancer.component';
import { FreelancerDashboardComponent } from './components/freelancer/freelancer-dashboard/freelancer-dashboard.component';
import { EmployeeDashboardComponent } from './components/employee/employee-dashboard/employee-dashboard.component';
import { NavigationComponent } from './components/employee/navigation/navigation.component';
import { EmployeeSettingsComponent } from './components/employee/employee-settings/employee-settings.component';
import { EmployeeGeneralComponent } from './components/employee/employee-settings/employee-general/employee-general.component';
import { EmployeeLocationsComponent } from './components/employee/employee-settings/employee-locations/employee-locations.component';
import { EmployeeEmployeesComponent } from './components/employee/employee-settings/employee-employees/employee-employees.component';
import { EmployeeStationsComponent } from './components/employee/employee-settings/employee-stations/employee-stations.component';
import { EmployeeSettingsNavigationComponent } from './components/employee/employee-settings/employee-settings-navigation/employee-settings-navigation.component';
import { EmployeeJobOffersComponent } from './components/employee/employee-job-offers/employee-job-offers.component';
import { LoadingSpinnerComponent } from './components/shared/loading-spinner/loading-spinner.component';
import { LoginComponent } from './components/shared/login/login.component';
import {TokenService} from "./services/auth/token.service";
import {IsAuthenticatedGuard} from "./guards/is-authenticated.guard";
import {CustomHttpClient} from "./services/custom-http-client";
import {UrlProvider} from "./services/url-provider";
import {UserService} from "./services/auth/user.service";
import { ApplicationUnavailableComponent } from './components/shared/application-unavailable/application-unavailable.component';
import {HttpErrorInterceptor} from "./classes/http-error-interceptor";
import { NewJobOfferComponent } from './components/employee/employee-job-offers/new-job-offer/new-job-offer.component';
import { JobOfferOverviewComponent } from './components/employee/employee-job-offers/job-offer-overview/job-offer-overview.component';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    FreelancerComponent,
    FreelancerDashboardComponent,
    EmployeeDashboardComponent,
    NavigationComponent,
    EmployeeSettingsComponent,
    EmployeeGeneralComponent,
    EmployeeLocationsComponent,
    EmployeeEmployeesComponent,
    EmployeeStationsComponent,
    EmployeeSettingsNavigationComponent,
    EmployeeJobOffersComponent,
    LoadingSpinnerComponent,
    LoginComponent,
    ApplicationUnavailableComponent,
    NewJobOfferComponent,
    JobOfferOverviewComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRouting,
    FormsModule
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
  bootstrap: [AppComponent]
})

export class AppModule { }
