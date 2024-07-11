import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TableService} from "../../../../../shared/service/http/table/table.service";
import {TableNames} from "../../../../../shared/model/server/database/table/Table";
import {PaginatorState} from "primeng/paginator";

@Component({
  selector: 'app-database-settings',
  templateUrl: './database-settings.component.html',
  styleUrl: './database-settings.component.scss'
})
export class DatabaseSettingsComponent implements OnInit {
  database: string | null = '';
  server: string | null = '';
  copyTableNames:TableNames[]=[]
  tableNames:TableNames[]=[]
  page: number = 0;
  rows: number = 5;

  constructor(private tableService:TableService) {}

  ngOnInit(): void {
    this.database=localStorage.getItem('database-settings-name');
    this.server=localStorage.getItem('server');
    this.tableService.getAllTableNames(this.server,this.database).subscribe(result => {
      for (let i = 0; i < result.length; i++) {
        this.tableNames.push({
          nr: i + 1,
          name: result[i]
        });
        this.copyTableNames.push({
          nr: i + 1,
          name: result[i]
        });
      }
      this.updatePage();
    });
  }

  change($event: PaginatorState) {
    this.page = $event.page!;
    this.rows = $event.rows!;
    this.updatePage();
  }

  updatePage(): void {
    const start = this.page! * this.rows!;
    const end = start + this.rows!;
    this.tableNames = this.copyTableNames.slice(start, end);
    console.log(this.page)
  }

  open(name:string) {

  }
}
