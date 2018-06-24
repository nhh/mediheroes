import { Injectable } from '@angular/core';
import {AbstractResourceService} from './abstract-resource.service';

@Injectable({
  providedIn: 'root'
})
export class MetricResourceService extends AbstractResourceService {

  private readonly basePath = "/api/v1/metrics";

  public getMetricsSummary() {
    return this.http.get(this.basePath + '/summary', this.authenticatedHttpOptions())
  }

}
