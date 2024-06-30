import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DashboardComponent} from "./core/page/dashboard/dashboard.component";
import {LoginComponent} from "./core/page/account/login/login.component";
import {authGuard} from "./shared/guard/auth.guard";


const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent,canActivate:[authGuard]},
  {path: 'login', component: LoginComponent},
  {path: '**', redirectTo: 'dashboard', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
