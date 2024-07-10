import {Component,  OnInit} from '@angular/core';
import {DatabaseService} from "../../../../shared/service/http/database/database.service";
import {DatabaseNameTable} from "../../../../shared/model/server/database/Database";
import {MenuItem} from "primeng/api";
import {PaginatorState} from "primeng/paginator";
import {Router} from "@angular/router";


@Component({
  selector: 'app-database',
  templateUrl: './database.component.html',
  styleUrl: './database.component.scss'
})
export class DatabaseComponent implements OnInit {
  databaseNameExist = false;
  filteredDatabaseNames: DatabaseNameTable[] = [];
  databaseNames: DatabaseNameTable[] = [];
  items: MenuItem[] = [
    {
      label: 'Delete', icon: 'pi pi-times', command: () => {
        this.delete();
      }
    }
  ];
  page: number = 0;
  rows: number = 5;

  constructor(private databaseService: DatabaseService,private router:Router) {
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

  private delete() {

  }

  open(databaseName: string) {
    localStorage.setItem('database-settings-name',databaseName);
    this.router.navigate(['/dashboard/database/settings'])
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
