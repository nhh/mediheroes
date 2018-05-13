import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {DashboardComponent} from './component/dashboard/dashboard.component';
import {IsAuthenticatedGuard} from '../shared/guard/is-authenticated.guard';
import {CustomHttpClient} from '../shared/service/custom-http-client';
import {UrlProvider} from '../shared/service/url-provider';
import {UserService} from '../shared/service/user.service';
import {HttpErrorInterceptor} from '../shared/class/http-error-interceptor';
import {FreelancerRouting} from './freelancer.routing';
import {FreelancerComponent} from './component/freelancer.component';
import {TokenService} from '../shared/service/auth/token.service';
import {NavigationComponent} from './component/navigation/navigation.component';


@NgModule({
  declarations: [
    DashboardComponent,
    FreelancerComponent,
    NavigationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    FreelancerRouting
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
    DashboardComponent,
    FreelancerComponent,
    FreelancerRouting,
    NavigationComponent
  ]
})

export class FreelancerModule { }
