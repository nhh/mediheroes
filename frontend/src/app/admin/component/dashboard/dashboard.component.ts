import { Component, OnInit } from '@angular/core';
import {MetricResourceService} from '../../../shared/service/resource/metric-resource.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private metricResourceService : MetricResourceService) { }

  metrics: any;
  isLoading = true;

  ngOnInit() {
    this.loadMetrics();
  }

  loadMetrics() {
    this.isLoading = true;
    this.metricResourceService.getMetricsSummary().subscribe(
      data => this.metrics = data,
      error => console.log(error),
      () => {
        setTimeout(() => {
          this.isLoading = false;
        }, 250)
      }
    );
  }

}
