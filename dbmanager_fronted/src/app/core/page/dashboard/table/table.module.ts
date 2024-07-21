import {NgModule} from "@angular/core";
import {TableRoutingModule} from "./table-routing.module";
import {ShowTableComponent} from "./show-table/show-table.component";
import {CreateTableComponent} from "./create-table/create-table.component";
import {PaginatorModule} from "primeng/paginator";
import {TableModule as PrimeTableModule} from "primeng/table";
import {CardModule} from "primeng/card";
import {CommonModule} from "@angular/common";

@NgModule({
  imports: [
    TableRoutingModule,
    PaginatorModule,
    PrimeTableModule,
    CardModule,
    CommonModule
  ],
  exports: [],
  declarations: [ShowTableComponent,CreateTableComponent]
})
export class TableModule {
}


