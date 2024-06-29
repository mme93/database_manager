import { Routes } from '@angular/router';
import {DashboardComponent} from "./core/page/dashboard/dashboard.component";
import {LoginComponent} from "./core/page/account/login/login.component";

export const routes: Routes = [
  {path: 'home',component:DashboardComponent},
  {path: 'login',component:LoginComponent}
];
