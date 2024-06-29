import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ToolbarModule} from "primeng/toolbar";
import {ButtonModule} from "primeng/button";
import {SidebarModule} from "primeng/sidebar";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,ToolbarModule,ButtonModule,SidebarModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Database Manager';
  gfg=false;
}
