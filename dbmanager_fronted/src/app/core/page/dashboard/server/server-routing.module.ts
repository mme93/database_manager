import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ServerComponent} from "./server.component";
import {ServerOverviewComponent} from "./server-overview/server-overview.component";

const routes: Routes = [
  {
    path: '',
    component: ServerComponent
  },
  {
    path: 'overview',
    component: ServerOverviewComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ServerRoutingModule {
}
