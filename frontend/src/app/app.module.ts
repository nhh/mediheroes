
import { NgModule } from '@angular/core';
import {EmployeeModule} from './employee/employee.module';
import {FreelancerModule} from './freelancer/freelancer.module';
import {AuthModule} from './auth/auth.module';
import {AppComponent} from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import {AuthRouting} from './auth/auth.routing';
import {EmployeeRouting} from './employee/employee.routing';
import {FreelancerRouting} from './freelancer/freelancer.routing';
import {SharedModule} from './shared/shared.module';
import {AdminRouting} from './admin/admin.routing';
import {AdminModule} from './admin/admin.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    EmployeeModule,
    EmployeeRouting,
    SharedModule,
    AuthModule,
    AuthRouting,
    AdminRouting,
    AdminModule,
    FreelancerModule,
    FreelancerRouting,
    FormsModule
  ],
  providers: [],
  bootstrap: [
    AppComponent
  ]
})

export class AppModule { }
