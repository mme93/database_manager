import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DashboardComponent} from "./core/page/dashboard/dashboard.component";
import {LoginComponent} from "./core/page/account/login/login.component";
import {AuthGuard} from "./shared/guard/auth.guard";


const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent,canActivate:[AuthGuard]},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
