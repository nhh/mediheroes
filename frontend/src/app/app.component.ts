import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  constructor(private http: HttpClient) { }

  configUrl = 'http://localhost:8080/users';

  getConfig() {
    return this.http.get<Config>(this.configUrl).subscribe(data => this.config = {
      firstname: data['firstname'],
      lastname:  data['lastname']
    });
  }

  ngOnInit() {
    console.log(this.getConfig());
  }

}

export interface Config {
  firstname: string;
  lastname: string;
}
