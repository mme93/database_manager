import {NgModule} from "@angular/core";
import {ServerRoutingModule} from "./server-routing.module";
import {ServerComponent} from "./server.component";
import { ServerInfoComponent } from './server-info/server-info.component';

@NgModule({
  imports: [
    ServerRoutingModule,
  ],
  exports: [],
  declarations: [ServerComponent, ServerInfoComponent]
})
export class ServerModule {
}


