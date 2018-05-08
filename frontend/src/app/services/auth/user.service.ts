import {Injectable} from '@angular/core';

@Injectable()
export class UserService {

  constructor() {

  }

  getCurrentUser() {
    let currentUser = localStorage.getItem("currentUser");

    if(currentUser == null){
      // handle not authenticated
    }
    return JSON.parse(currentUser);
  }

}
