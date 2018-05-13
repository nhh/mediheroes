import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {IsAuthenticatedGuard} from '../shared/guard/is-authenticated.guard';
import {CustomHttpClient} from '../shared/service/custom-http-client';
import {UrlProvider} from '../shared/service/url-provider';
import {UserService} from '../shared/service/user.service';
import {HttpErrorInterceptor} from '../shared/class/http-error-interceptor';
import {LoginComponent} from './component/login/login.component';
import {RegisterComponent} from './component/register/register.component';
import {TokenService} from '../shared/service/auth/token.service';
import {AuthRouting} from './auth.routing';
import {AuthComponent} from './component/auth.component';


@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    AuthComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AuthRouting
  ],
  providers: [
    IsAuthenticatedGuard,
    CustomHttpClient,
    TokenService,
    UrlProvider,
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    }
  ],
  exports: [
    LoginComponent,
    RegisterComponent,
    AuthComponent
  ],
  bootstrap: [
    AuthComponent
  ]
})

export class AuthModule { }
