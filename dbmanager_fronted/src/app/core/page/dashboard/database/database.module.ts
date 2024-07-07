import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {DatabaseRoutingModule} from "./database-routing.module";

@NgModule({
  imports: [
    DatabaseRoutingModule,
    CommonModule
  ],
  exports: [],
  declarations: []
})
export class DatabaseModule {
}
