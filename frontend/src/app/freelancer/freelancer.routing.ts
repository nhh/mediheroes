import { Routes, RouterModule } from "@angular/router";
import { NgModule } from '@angular/core';
import { DashboardComponent} from './component/dashboard/dashboard.component';
import {FreelancerComponent} from './component/freelancer.component';

const routes: Routes = [
  {
    path: 'freelancer',
    component: FreelancerComponent,
    children: [
      {
        path: "dashboard",
        component: DashboardComponent
      }
    ]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class FreelancerRouting {}
