import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injector, Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export abstract class AbstractResourceService {

  protected readonly http: HttpClient;

  constructor(injector: Injector) {
    this.http = injector.get(HttpClient);
  }


  protected authenticatedHttpOptions() : any {
    return {
      headers: new HttpHeaders({
        'X-Requested-With': 'XMLHttpRequest',
        'X-AUTH-TOKEN': localStorage.getItem('x-auth-token')
      }),
      withCredentials: true
    };
  }

}
