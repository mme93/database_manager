import { Injectable } from '@angular/core';
import {Login} from "../../model/LoginInformation";
import {delay} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor() { }

  async  login(login: Login):Promise<boolean> {
    await delay(2000);
    console.log(login)
    return true;
  }
}
