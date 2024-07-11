import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {DatabaseRoutingModule} from "./database-routing.module";
import { DatabaseSettingsComponent } from './database-settings/database-settings.component';
import {CardModule} from "primeng/card";
import {ButtonModule} from "primeng/button";
import {PaginatorModule} from "primeng/paginator";
import {TableModule} from "primeng/table";

@NgModule({
  imports: [
    DatabaseRoutingModule,
    CommonModule,
    CardModule,
    ButtonModule,
    PaginatorModule,
    TableModule
  ],
  exports: [],
  declarations: [
    DatabaseSettingsComponent
  ]
})
export class DatabaseModule {
}
