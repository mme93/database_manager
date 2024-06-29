import { Component } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {LoginService} from "../../../../shared/http/login/login.service";
import {Server} from "../../../../shared/model/LoginInformation";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  isLogin = false;
  loginForm = this.fb.group({
    userName: ["", Validators.compose([Validators.required, Validators.minLength(2)])],
    passWord: ['', Validators.required],
  })
  username = '';
  password = '';
  servers: Server[] = [
    {name: 'Cloud XXL', code: 'CX'},
    {name: 'Cloud Server', code: 'CS'}
  ];

  selectedServer: Server = {name: 'Cloud XXL', code: 'CX'};

  constructor(private loginService: LoginService, private fb: FormBuilder) {
  }

  login() {
    this.isLogin = true;
    this.loginService.login({
      username: this.username,
      password: this.password,
      servername: this.selectedServer.name,
    })
  }
}
