import {Component, OnInit} from '@angular/core';
import {TableService} from "../../../../../shared/service/http/table/table.service";
import {ActivatedRoute} from "@angular/router";
import {DatabaseTableCell, DatabaseTableView} from "../../../../../shared/model/table/TableView";

@Component({
  selector: 'app-show-table',
  templateUrl: './show-table.component.html',
  styleUrl: './show-table.component.scss'
})
export class ShowTableComponent implements OnInit {
  databaseTableView: DatabaseTableView = {};
  copyDatabaseTableView: DatabaseTableView = {};
  tableName: string | null = '';
  databaseName: string | null = '';
  page: number = 0;
  rows: number = 5;

  constructor(private tableService: TableService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.tableName = params['tableName'];
      this.databaseName = params['databaseName'];
      this.tableService.getTableByNameAndDatabase(localStorage.getItem('server'), params['databaseName'], params['tableName'])
        .subscribe(result => {
          this.databaseTableView = result;
          this.copyDatabaseTableView = result;
        });
    });
  }
  /**
  change(event: any): void {
    this.page = event.page;
    this.rows = event.rows;
    this.updatePage();
  }

  updatePage(): void {

  }
   **/


}
