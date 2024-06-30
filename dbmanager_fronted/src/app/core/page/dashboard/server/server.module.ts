import {NgModule} from "@angular/core";
import {ServerRoutingModule} from "./server-routing.module";
import { ServerInfoComponent } from './server-info/server-info.component';

@NgModule({
  imports: [
    ServerRoutingModule,
  ],
  exports: [],
  declarations: [ServerInfoComponent]
})
export class ServerModule {
}


