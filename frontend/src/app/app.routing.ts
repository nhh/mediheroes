import { Routes, RouterModule } from "@angular/router";
import { NgModule } from '@angular/core';

import { EmployeeComponent } from "./components/employee/employee.component";
import { FreelancerComponent } from "./components/freelancer/freelancer/freelancer.component";
import {FreelancerDashboardComponent} from "./components/freelancer/freelancer-dashboard/freelancer-dashboard.component";
import {EmployeeDashboardComponent} from "./components/employee/employee-dashboard/employee-dashboard.component";
import {AppComponent} from "./components/app.component";
import {EmployeeSettingsComponent} from "./components/employee/employee-settings/employee-settings.component";
import {EmployeeGeneralComponent} from "./components/employee/employee-settings/employee-general/employee-general.component";
import {EmployeeLocationsComponent} from "./components/employee/employee-settings/employee-locations/employee-locations.component";
import {EmployeeStationsComponent} from "./components/employee/employee-settings/employee-stations/employee-stations.component";
import {EmployeeJobOffersComponent} from "./components/employee/employee-job-offers/employee-job-offers.component";



const routes: Routes = [
  {
    path: 'employee',
    component: EmployeeComponent,
    children: [
      {
        path: 'job-offers', component: EmployeeJobOffersComponent
      },
      {
        path: 'dashboard', component: EmployeeDashboardComponent
      },
      {
        path: 'settings', component: EmployeeSettingsComponent,
        children: [
          {path: 'general', component: EmployeeGeneralComponent},
          {path: 'locations', component: EmployeeLocationsComponent},
          {path: 'stations', component: EmployeeStationsComponent},
          {path: 'employees', component: EmployeeStationsComponent},
        ]
      }
    ]
  },
  {
    path: 'freelancer',
    component: FreelancerComponent,
    children: [
      {path: 'dashboard', component: FreelancerDashboardComponent}
    ]
  },
  {
    path: '',
    component: AppComponent,
  }
];


@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class AppRouting {}
