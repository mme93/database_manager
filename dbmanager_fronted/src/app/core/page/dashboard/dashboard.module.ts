import {DashboardRoutingModule} from "./dashboard-routing.module";
import {NgModule} from "@angular/core";
import {DashboardComponent} from "./dashboard.component";
import { ServerOverviewComponent } from './server/server-overview/server-overview.component';
import { DatabaseComponent } from './database/database.component';
import { TableComponent } from './table/table.component';
import {CardModule} from "primeng/card";
import {CommonModule} from "@angular/common";
import {SplitterModule} from "primeng/splitter";

@NgModule({
  imports: [
    DashboardRoutingModule,
    CardModule,
    CommonModule,
    SplitterModule
  ],
  exports: [],
  declarations: [DashboardComponent, ServerOverviewComponent, DatabaseComponent, TableComponent]
})
export class DashboardModule {
}


