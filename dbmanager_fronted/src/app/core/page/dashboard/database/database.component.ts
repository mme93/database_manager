import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {DatabaseService} from "../../../../shared/service/http/database/database.service";
import {DatabaseNameTable} from "../../../../shared/model/server/database/Database";
import {MenuItem} from "primeng/api";
import {PaginatorState} from "primeng/paginator";



@Component({
  selector: 'app-database',
  templateUrl: './database.component.html',
  styleUrl: './database.component.scss'
})
export class DatabaseComponent implements OnInit {
  databaseNameExist = false;
  filteredDatabaseNames: DatabaseNameTable[] = [];
  databaseNames: DatabaseNameTable[] = [];
  items: MenuItem[]= [
    {label: 'Update', icon: 'pi pi-refresh', command: () => {
        this.update();
      }},
    {label: 'Delete', icon: 'pi pi-times', command: () => {
        this.delete();
      }},
    {label: 'Angular.io', icon: 'pi pi-info', url: 'http://angular.io'},
    {separator:true},
    {label: 'Setup', icon: 'pi pi-cog', routerLink: ['/setup']}
  ];
  page: number  = 0;
  rows: number  = 5;

  constructor(private databaseService: DatabaseService) {
  }

  ngOnInit(): void {
    this.databaseService.getAllDatabaseNames(localStorage.getItem('server')).subscribe(result => {
      for (let i = 0; i < result.length; i++) {
        this.databaseNames.push({
          nr: i + 1,
          name: result[i]
        });
        this.filteredDatabaseNames.push({
          nr: i + 1,
          name: result[i]
        });
      }
      this.updatePage();
    })

  }

  private update() {

  }

  private delete() {

  }

  open(info: string) {

  }


  change($event: PaginatorState) {
    this.page = $event.page!;
    this.rows = $event.rows!;
    this.updatePage();
  }

  updatePage(): void {
    const start = this.page! * this.rows!;
    const end = start + this.rows!;
    this.filteredDatabaseNames = this.databaseNames.slice(start, end);
  }
}
