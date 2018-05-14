import { Routes, RouterModule } from "@angular/router";
import { NgModule } from '@angular/core';

import {SettingsComponent} from "./components/settings/settings.component";
import {GeneralComponent} from "./components/settings/general/general.component";
import {LocationsComponent} from "./components/settings/locations/locations.component";
import {StationsComponent} from "./components/settings/stations/stations.component";
import {IsAuthenticatedGuard} from "../shared/guard/is-authenticated.guard";
import {NewJobOfferComponent} from "./components/job-offers/new-job-offer/new-job-offer.component";
import {JobOfferOverviewComponent} from "./components/job-offers/job-offer-overview/job-offer-overview.component";
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {EmployeeComponent} from './components/employee.component';
import {ShowJobOfferComponent} from './components/job-offers/show-job-offer/show-job-offer.component';

const routes: Routes = [
  {
    path: "employee",
    canActivate: [IsAuthenticatedGuard],
    canActivateChild: [IsAuthenticatedGuard],
    component: EmployeeComponent,
    children: [
      {
        path: 'job-offers',
        children: [
          {
            path: "",
            component: JobOfferOverviewComponent
          },
          {
            path: 'new',
            component: NewJobOfferComponent
          },
          {
            path: ':id',
            component: ShowJobOfferComponent
          }
        ]
      },
      {
        path: 'settings', component: SettingsComponent,
        children: [
          {
            path: 'general', component: GeneralComponent
          },
          {
            path: 'locations', component: LocationsComponent
          },
          {
            path: 'stations', component: StationsComponent
          },
          {path: 'employees', component: StationsComponent},
        ]
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      }
    ]
  }
];


@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class EmployeeRouting {}
