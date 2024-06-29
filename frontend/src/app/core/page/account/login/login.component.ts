import {Component} from '@angular/core';
import {CardModule} from "primeng/card";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {SelectButtonModule} from "primeng/selectbutton";
import {DropdownModule} from "primeng/dropdown";
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {Server} from "../../../../shared/model/LoginInformation";
import {LoginService} from "../../../../shared/http/login/login.service";
import {CommonModule} from "@angular/common";


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,CardModule, ButtonModule, InputTextModule, SelectButtonModule, DropdownModule, FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

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
    /*
    this.loginService.login({
      username: this.username,
      password: this.password,
      servername: this.selectedServer.name,
    })

     */
  }
}
