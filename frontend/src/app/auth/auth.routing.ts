import { Routes, RouterModule } from "@angular/router";
import { NgModule } from '@angular/core';
import {LoginComponent} from './component/login/login.component';
import {RegisterComponent} from './component/register/register.component';

const routes: Routes = [
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  }
];


@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class AuthRouting {}
