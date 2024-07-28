import {NgModule} from "@angular/core";
import {TableRoutingModule} from "./table-routing.module";
import {ShowTableComponent} from "./show-table/show-table.component";
import {CreateTableComponent} from "./create-table/create-table.component";
import {PaginatorModule} from "primeng/paginator";
import {TableModule as PrimeTableModule} from "primeng/table";
import {CardModule} from "primeng/card";
import {CommonModule} from "@angular/common";
import {Ripple} from "primeng/ripple";
import {Button, ButtonDirective} from "primeng/button";
import {TooltipModule} from "primeng/tooltip";
import {DialogModule} from "primeng/dialog";
import {ListboxModule} from "primeng/listbox";
import {DialogService} from "primeng/dynamicdialog";
import {CheckboxModule} from "primeng/checkbox";
import {InputTextModule} from "primeng/inputtext";

@NgModule({
    imports: [
        TableRoutingModule,
        PaginatorModule,
        PrimeTableModule,
        CardModule,
        CommonModule,
        Ripple,
        ButtonDirective,
        TooltipModule,
        DialogModule,
        Button,
        ListboxModule,
        CheckboxModule,
        InputTextModule
    ],
  providers:[
    DialogService
  ],
  exports: [],
  declarations: [ShowTableComponent,CreateTableComponent]
})
export class TableModule {
}


