import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {DatabaseComponent} from "./database.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full'
  },
  {
    path: 'overview',
    component: DatabaseComponent
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DatabaseRoutingModule {
}
