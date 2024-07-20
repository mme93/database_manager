import {NgModule} from "@angular/core";
import {TableRoutingModule} from "./table-routing.module";
import {ShowTableComponent} from "./show-table/show-table.component";
import {CreateTableComponent} from "./create-table/create-table.component";

@NgModule({
  imports: [
    TableRoutingModule
  ],
  exports: [],
  declarations: [ShowTableComponent,CreateTableComponent]
})
export class TableModule {
}


