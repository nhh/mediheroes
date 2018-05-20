import { Routes, RouterModule } from "@angular/router";
import { NgModule } from '@angular/core';
import { DashboardComponent} from './component/dashboard/dashboard.component';
import {FreelancerComponent} from './component/freelancer.component';
import {SettingsComponent} from './component/settings/settings.component';
import {JobOffersComponent} from './component/job-offers/job-offers.component';

const routes: Routes = [
  {
    path: 'freelancer',
    component: FreelancerComponent,
    children: [
      {
        path: "dashboard",
        component: DashboardComponent
      },
      {
        path: "settings",
        component: SettingsComponent
      },
      {
        path: "job-offers",
        component: JobOffersComponent
      }
    ]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class FreelancerRouting {}
