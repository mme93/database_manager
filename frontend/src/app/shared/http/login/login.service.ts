import {Injectable} from '@angular/core';
import {Login} from "../../model/LoginInformation";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor() {
  }

  login(login: Login): boolean {
    console.log(login)
    return true;
  }
}
