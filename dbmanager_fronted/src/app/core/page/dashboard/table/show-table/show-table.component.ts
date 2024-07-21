import {Component, OnInit} from '@angular/core';
import {TableService} from "../../../../../shared/service/http/table/table.service";
import {ActivatedRoute} from "@angular/router";
import { DatabaseTableView} from "../../../../../shared/model/table/TableView";

@Component({
  selector: 'app-show-table',
  templateUrl: './show-table.component.html',
  styleUrl: './show-table.component.scss'
})
export class ShowTableComponent implements OnInit {
  databaseTableView: DatabaseTableView = {};
  tableName: string | null = '';
  databaseName: string | null = '';

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
            this.databaseTableView = result || { metaData: [], databaseTableRows: [] };
          }, error => {
            this.databaseTableView = { metaData: [], databaseTableRows: [] };
          });
      }
    });
  }
}
