import {Component} from '@angular/core';
import {MenuItem} from "primeng/api";
import {MenubarService} from "./shared/ui/menubar/menubar.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'dbmanager_fronted';
  items: MenuItem[] = [];
  isLogin = true;

  constructor(private menubarService:MenubarService) {
  }

  ngOnInit() {
    this.items = this.menubarService.getMenubarItems("Cloud XXL");
  }

  logout() {
    this.isLogin = false;
    this.items=[];
  }
}
