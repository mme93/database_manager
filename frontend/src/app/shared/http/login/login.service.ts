import {Injectable} from '@angular/core';
import {Login} from "../../model/LoginInformation";
import { Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private router:Router) {
  }

  login(login: Login): boolean {
    //console.log(login)
    this.router.navigate(['/home']);
    return true;
  }
}
