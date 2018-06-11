import { Routes, RouterModule } from "@angular/router";
import { NgModule } from '@angular/core';
import { DashboardComponent} from './component/dashboard/dashboard.component';
import {FreelancerComponent} from './component/freelancer.component';
import {SettingsComponent} from './component/settings/settings.component';
import {IsFreelancerGuard} from '../shared/guard/is-freelancer.guard';
import {IsAuthenticatedGuard} from '../shared/guard/is-authenticated.guard';
import {ShowComponent} from './component/job-offers/show/show.component';
import {IndexComponent} from './component/job-offers/index/index.component';
import {ProfileComponent} from './component/settings/profile/profile.component';

const routes: Routes = [
  {
    path: 'freelancer',
    canActivate: [IsFreelancerGuard, IsAuthenticatedGuard],
    canActivateChild : [IsFreelancerGuard, IsAuthenticatedGuard],
    component: FreelancerComponent,
    children: [
      {
        path: "dashboard",
        component: DashboardComponent
      },
      {
        path: "settings",
        component: SettingsComponent,
        children: [
          {
            path: "profile",
            component: ProfileComponent
          }
        ]
      },
      {
        path: "job-offers",
        children: [
          {
            path: "",
            component: IndexComponent
          },
          {
            path: ":id",
            component: ShowComponent
          }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class FreelancerRouting {}
