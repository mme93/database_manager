import {DashboardRoutingModule} from "./dashboard-routing.module";
import {NgModule} from "@angular/core";
import {DashboardComponent} from "./dashboard.component";
import {ServerOverviewComponent} from './server/server-overview/server-overview.component';
import {DatabaseComponent} from './database/database.component';
import {TableComponent} from './table/table.component';
import {CardModule} from "primeng/card";
import {CommonModule} from "@angular/common";
import {SplitterModule} from "primeng/splitter";
import {TreeModule} from "primeng/tree";
import {DividerModule} from "primeng/divider";
import {ButtonModule} from "primeng/button";
import { ShowTableComponent } from './table/show-table/show-table.component';
import { CreateTableComponent } from './table/create-table/create-table.component';
import {InputTextModule} from "primeng/inputtext";

@NgModule({
  imports: [
    DashboardRoutingModule,
    CardModule,
    CommonModule,
    SplitterModule,
    TreeModule,
    DividerModule,
    ButtonModule,
    InputTextModule
  ],
  exports: [],
  declarations: [DashboardComponent, ServerOverviewComponent, DatabaseComponent, TableComponent, ShowTableComponent, CreateTableComponent]
})
export class DashboardModule {
}


