import {Component} from '@angular/core';
import {CardModule} from "primeng/card";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {SelectButtonChangeEvent, SelectButtonModule} from "primeng/selectbutton";
import {DropdownModule} from "primeng/dropdown";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Server} from "../../../../shared/model/LoginInformation";
import {LoginService} from "../../../../shared/http/login/login.service";


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CardModule, ButtonModule, InputTextModule, SelectButtonModule, DropdownModule,FormsModule,ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  username='';
  password='';
  servers: Server[] = [
    {name: 'Cloud XXL', code: 'CX'},
    {name: 'Cloud Server', code: 'CS'}
  ];

  selectedServer: Server={name: 'Cloud XXL', code: 'CX'};

  constructor(private loginService:LoginService) {
  }

  login() {
    this.loginService.login({
      username: this.username,
      password: this.password,
      servername: this.selectedServer.name,
    })
  }
}
