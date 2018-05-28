import {NgModule} from '@angular/core';
import {LoadingSpinnerComponent} from './component/loading-spinner/loading-spinner.component';
import { BannerComponent } from './component/banner/banner.component';
import { ToastComponent } from './component/toast/toast.component';
import {BrowserModule} from '@angular/platform-browser';

@NgModule({
  declarations: [
    LoadingSpinnerComponent,
    BannerComponent,
    ToastComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  exports: [
    LoadingSpinnerComponent,
    BannerComponent,
    ToastComponent
  ]
})

export class SharedModule { }
