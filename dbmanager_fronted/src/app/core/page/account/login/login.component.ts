import { Component } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {LoginService} from "../../../../shared/service/http/login/login.service";
import {Server} from "../../../../shared/model/LoginInformation";
import {AppComponent} from "../../../../app.component";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  isLogin = false;
  loginForm = this.fb.group({
    userName: ["mamei", Validators.compose([Validators.required, Validators.minLength(2)])],
    passWord: ['tes123', Validators.required],
  })
  username = '';
  password = '';
  servers: Server[] = [
    {name: 'Cloud XXL', code: 'CX'},
    {name: 'Cloud Server', code: 'CS'}
  ];

  selectedServer: Server = {name: 'Cloud XXL', code: 'CX'};

  constructor(private loginService: LoginService, private fb: FormBuilder,private appComponent:AppComponent) {
    this.appComponent.logout();
  }

  login() {
    this.isLogin = true;
    this.appComponent.loadItems();
    this.loginService.login({
      username: this.username,
      password: this.password,
      servername: this.selectedServer.name,
    })

  }
}
