import {DashboardRoutingModule} from "./dashboard-routing.module";
import {NgModule} from "@angular/core";
import {DashboardComponent} from "./dashboard.component";
import { ServerComponent } from './server/server.component';
import { ServerOverviewComponent } from './server/server-overview/server-overview.component';

@NgModule({
  imports: [
    DashboardRoutingModule,
  ],
  exports: [],
  declarations: [DashboardComponent, ServerComponent, ServerOverviewComponent]
})
export class DashboardModule {
}


