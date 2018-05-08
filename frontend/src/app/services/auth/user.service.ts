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

  getCurrentCompany(){
    let user = localStorage.getItem("currentUser");

    if (user == null){

    }

    return JSON.parse(user).company
  }

}
