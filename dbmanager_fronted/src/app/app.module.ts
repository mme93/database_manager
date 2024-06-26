import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './core/page/account/login/login.component';
import { DashboardComponent } from './core/page/dashboard/dashboard.component';
import {ButtonModule} from "primeng/button";
import {CommonModule} from "@angular/common";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CardModule} from "primeng/card";
import {SplitButtonModule} from "primeng/splitbutton";
import {InputTextModule} from "primeng/inputtext";
import { SelectButtonModule} from "primeng/selectbutton";
import {MenubarModule} from "primeng/menubar";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent
  ],
    imports: [
        BrowserAnimationsModule,
        BrowserModule,
        CommonModule,
        AppRoutingModule,
        ButtonModule,
        FormsModule,
        ReactiveFormsModule,
        CardModule,
        SplitButtonModule,
        InputTextModule,
        SelectButtonModule,
        MenubarModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
