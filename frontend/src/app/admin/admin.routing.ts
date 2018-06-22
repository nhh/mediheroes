import { Routes, RouterModule } from "@angular/router";
import { NgModule } from '@angular/core';

import {IsAdminGuard} from '../shared/guard/is-admin.guard';
import {AdminComponent} from './component/admin.component';
import {DashboardComponent} from './component/dashboard/dashboard.component';
import {UsersComponent} from './component/users/users.component';
import {CompaniesComponent} from './component/companies/companies.component';
import {IsAuthenticatedGuard} from '../shared/guard/is-authenticated.guard';
import {JobOffersComponent} from './component/job-offers/job-offers.component';
import {SettingsComponent} from './component/settings/settings.component';

const routes: Routes = [
  {
    path: "admin",
    canActivate: [IsAdminGuard, IsAuthenticatedGuard],
    canActivateChild: [IsAdminGuard, IsAuthenticatedGuard],
    component: AdminComponent,
    children: [
      {
        path: "dashboard",
        component: DashboardComponent
      },
      {
        path: "users",
        component: UsersComponent
      },
      {
        path: "companies",
        component: CompaniesComponent
      },
      {
        path: "job-offers",
        component: JobOffersComponent
      },
      {
        path: "settings",
        component: SettingsComponent
      }

    ]
  }
];


@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class AdminRouting {}
