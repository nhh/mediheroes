import {Injectable} from '@angular/core';

@Injectable()
export class UserService {

  constructor() {

  }

  getCurrentUser() {
    const currentUser = localStorage.getItem("currentUser");

    if(currentUser == null){
      // handle not authenticated
    }
    return JSON.parse(currentUser);
  }

  getCurrentCompany(){
    const user = localStorage.getItem("currentUser");

    if (user == null){

    }

    return JSON.parse(user).company
  }

  isEmployee(): boolean {
    const user = JSON.parse(localStorage.getItem("currentUser"));
    return user.roles.includes("EMPLOYEE")
  }

  isOwner(): boolean {
    const user = JSON.parse(localStorage.getItem("currentUser"));
    return user.roles.includes("OWNER")
  }

  isFreelancer(): boolean {
    const user = JSON.parse(localStorage.getItem("currentUser"));
    return user.roles.includes("FREELANCER")
  }

}
