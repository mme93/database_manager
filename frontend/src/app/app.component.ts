import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ToolbarModule} from "primeng/toolbar";
import {ButtonModule} from "primeng/button";
import {MenuItem} from "primeng/api";
import {SplitButtonModule} from "primeng/splitbutton";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,ToolbarModule,ButtonModule,SplitButtonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Database Manager';

  items: MenuItem[]=[];

  ngOnInit() {
    this.items = [
      {
        label: 'Update',
        icon: 'pi pi-refresh'
      },
      {
        label: 'Delete',
        icon: 'pi pi-times'
      },
      {
        label: 'Angular Website',
        icon: 'pi pi-external-link',
        url: 'http://angular.io'
      },
      {
        label: 'Router',
        icon: 'pi pi-upload',
        routerLink: '/fileupload'
      }
    ];
  }
}
