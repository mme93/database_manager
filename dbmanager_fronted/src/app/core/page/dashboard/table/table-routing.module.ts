import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ShowTableComponent} from "./show-table/show-table.component";
import {TableComponent} from "./table.component";

const routes: Routes = [
  {
    path: '',
    component: TableComponent
  },
  {
    path: 'show',
    component: ShowTableComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TableRoutingModule {
}
