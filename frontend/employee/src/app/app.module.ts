import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRouting} from "./app.routing";
import { AppComponent } from './components/app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { SettingsComponent } from './components/settings/settings.component';
import { GeneralComponent } from './components/settings/general/general.component';
import { LocationsComponent } from './components/settings/locations/locations.component';
import { StationsComponent } from './components/settings/stations/stations.component';
import { SettingsNavigationComponent } from './components/settings/settings-navigation/settings-navigation.component';
import { JobOffersComponent } from './components/job-offers/job-offers.component';
import { LoadingSpinnerComponent } from './components/shared/loading-spinner/loading-spinner.component';
import { LoginComponent } from './components/shared/login/login.component';
import {TokenService} from "./services/auth/token.service";
import {IsAuthenticatedGuard} from "./guards/is-authenticated.guard";
import {CustomHttpClient} from "./services/custom-http-client";
import {UrlProvider} from "./services/url-provider";
import {UserService} from "./services/auth/user.service";
import { ApplicationUnavailableComponent } from './components/shared/application-unavailable/application-unavailable.component';
import {HttpErrorInterceptor} from "./classes/http-error-interceptor";
import { NewJobOfferComponent } from './components/job-offers/new-job-offer/new-job-offer.component';
import {JobOfferOverviewComponent} from './components/job-offers/job-offer-overview/job-offer-overview.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    NavigationComponent,
    SettingsComponent,
    GeneralComponent,
    LocationsComponent,
    StationsComponent,
    SettingsNavigationComponent,
    JobOffersComponent,
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
