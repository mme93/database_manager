import {Component, OnInit} from '@angular/core';
import {TableService} from "../../../../../shared/service/http/table/table.service";
import {ActivatedRoute} from "@angular/router";
import {DatabaseTableRow, DatabaseTableView} from "../../../../../shared/model/table/TableView";

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
  pagedRows: DatabaseTableRow[] = [];

  constructor(private tableService: TableService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.tableName = params['tableName'];
      this.databaseName = params['databaseName'];
      const server = localStorage.getItem('server');
      if (server) {
        this.tableService.getTableByNameAndDatabase(server, this.databaseName, this.tableName)
          .subscribe(result => {
            this.databaseTableView = result || {metaData: [], databaseTableRows: []};
            this.copyDatabaseTableView = result || {metaData: [], databaseTableRows: []};
            this.updatePage();
          }, error => {
            this.databaseTableView = {metaData: [], databaseTableRows: []};
            this.updatePage();
          });
      }
    });
  }

  change(event: any): void {
    this.page = event.page;
    this.rows = event.rows;
    this.updatePage();
  }

  updatePage(): void {
    const start = this.page * this.rows;
    const end = start + this.rows;
    if (this.copyDatabaseTableView.databaseTableRows) {
      this.databaseTableView.databaseTableRows = this.copyDatabaseTableView.databaseTableRows.slice(start, end);
    }
  }

}
