import { Routes, RouterModule } from "@angular/router";
import { NgModule } from '@angular/core';
import { DashboardComponent} from './component/dashboard/dashboard.component';
import {FreelancerComponent} from './component/freelancer.component';
import {SettingsComponent} from './component/settings/settings.component';
import {IsFreelancerGuard} from '../shared/guard/is-freelancer.guard';
import {IsAuthenticatedGuard} from '../shared/guard/is-authenticated.guard';
import {GeneralComponent} from './component/job-offers/general/general.component';
import {LocationsComponent} from './component/job-offers/locations/locations.component';
import {OverviewComponent} from './component/job-offers/overview/overview.component';
import {JobOfferComponent} from './component/job-offers/job-offer.component';
import {StationsComponent} from './component/job-offers/stations/stations.component';
import {ApplicationComponent} from './component/job-offers/application/application.component';
import {AddressComponent} from './component/settings/address/address.component';
import {BlacklistComponent} from './component/settings/blacklist/blacklist.component';
import {DocumentsComponent} from './component/settings/documents/documents.component';
import {SettingsGeneralComponent} from './component/settings/settings-general/settings-general.component';

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
            path: "general",
            component: SettingsGeneralComponent
          },
          {
            path: "address",
            component: AddressComponent
          },
          {
            path: "blacklist",
            component: BlacklistComponent
          },
          {
            path: "documents",
            component: DocumentsComponent
          }
        ]
      },
      {
        path: "job-offers",
        children: [
          {
            path: "overview",
            component: OverviewComponent
          },
          {
            path: ":id",
            component: JobOfferComponent,
            children: [
              {
                path: "locations",
                component: LocationsComponent
              },
              {
                path: "general",
                component: GeneralComponent
              },
              {
                path: "stations",
                component: StationsComponent
              },
              {
                path: "application",
                component: ApplicationComponent
              }
            ]
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
