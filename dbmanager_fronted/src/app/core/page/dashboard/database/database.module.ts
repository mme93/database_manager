import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {DatabaseRoutingModule} from "./database-routing.module";
import { DatabaseSettingsComponent } from './database-settings/database-settings.component';

@NgModule({
  imports: [
    DatabaseRoutingModule,
    CommonModule
  ],
  exports: [],
  declarations: [
    DatabaseSettingsComponent
  ]
})
export class DatabaseModule {
}
