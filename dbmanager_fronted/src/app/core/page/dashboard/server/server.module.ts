import {NgModule} from "@angular/core";
import {ServerRoutingModule} from "./server-routing.module";
import {ServerComponent} from "./server.component";

@NgModule({
  imports: [
    ServerRoutingModule,
  ],
  exports: [],
  declarations: [ServerComponent]
})
export class ServerModule {
}


