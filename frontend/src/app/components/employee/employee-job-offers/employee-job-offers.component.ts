import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employee-job-offers',
  templateUrl: './employee-job-offers.component.html',
  styleUrls: ['./employee-job-offers.component.css']
})
export class EmployeeJobOffersComponent implements OnInit {

  constructor() {
  }

  isLoading = true;

  items = [
    {title: 'Title', members: 2}
  ];

  ngOnInit() {
    this.isLoading = false;
  }

  addItem(){
    this.isLoading = true;
    setTimeout(() => {
      this.items.push({title: "Hello World", members: 1})
      this.isLoading = false;
    }, 1500);
  }

}
