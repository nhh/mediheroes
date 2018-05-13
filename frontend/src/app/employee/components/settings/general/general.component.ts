import { Component, OnInit } from '@angular/core';
import {CustomHttpClient} from "../../../../shared/service/custom-http-client";
import { UrlProvider } from '../../../../shared/service/url-provider';

@Component({
  selector: 'app-employee-general',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.css']
})
export class GeneralComponent implements OnInit {

  public isLoading : boolean = true;
  private company : any = {};

  constructor(private http : CustomHttpClient, private urlProvider : UrlProvider) { }

  ngOnInit() {
    this.http.get(this.urlProvider.currentCompanyResource()).subscribe(
      (data) => this.company = data,
      (error) => console.log(error),
      () => setTimeout(() => { this.isLoading = false }, 250)
    )
  }

}
