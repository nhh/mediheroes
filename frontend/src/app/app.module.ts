import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';


import { AppRouting} from "./app.routing";
import { AppComponent } from './components/app.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { FreelancerComponent } from './components/freelancer/freelancer/freelancer.component';
import { FreelancerDashboardComponent } from './components/freelancer/freelancer-dashboard/freelancer-dashboard.component';
import { EmployeeDashboardComponent } from './components/employee/employee-dashboard/employee-dashboard.component';
import { NavigationComponent } from './components/employee/navigation/navigation.component';
import { EmployeeSettingsComponent } from './components/employee/employee-settings/employee-settings.component';
import { EmployeeGeneralComponent } from './components/employee/employee-settings/employee-general/employee-general.component';
import { EmployeeLocationsComponent } from './components/employee/employee-settings/employee-locations/employee-locations.component';
import { EmployeeEmployeesComponent } from './components/employee/employee-settings/employee-employees/employee-employees.component';
import { EmployeeStationsComponent } from './components/employee/employee-settings/employee-stations/employee-stations.component';
import { EmployeeSettingsNavigationComponent } from './components/employee/employee-settings/employee-settings-navigation/employee-settings-navigation.component';
import { EmployeeJobOffersComponent } from './components/employee/employee-job-offers/employee-job-offers.component';
import { LoadingSpinnerComponent } from './components/shared/loading-spinner/loading-spinner.component';


@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    FreelancerComponent,
    FreelancerDashboardComponent,
    EmployeeDashboardComponent,
    NavigationComponent,
    EmployeeSettingsComponent,
    EmployeeGeneralComponent,
    EmployeeLocationsComponent,
    EmployeeEmployeesComponent,
    EmployeeStationsComponent,
    EmployeeSettingsNavigationComponent,
    EmployeeJobOffersComponent,
    LoadingSpinnerComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRouting
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
