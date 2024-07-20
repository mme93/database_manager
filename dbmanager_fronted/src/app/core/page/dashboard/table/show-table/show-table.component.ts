import {Component, OnInit} from '@angular/core';
import {TableService} from "../../../../../shared/service/http/table/table.service";

@Component({
  selector: 'app-show-table',
  templateUrl: './show-table.component.html',
  styleUrl: './show-table.component.scss'
})
export class ShowTableComponent implements OnInit {

  tableName: string | null = '';
  databaseName: string | null = '';

  constructor(private tableService: TableService) {
  }

  ngOnInit(): void {
    this.tableName = localStorage.getItem('openTable');
    this.databaseName = localStorage.getItem('databaseName');
    const serverName = localStorage.getItem('server');

    this.tableService.getTableByNameAndDatabase(serverName, this.databaseName, this.tableName)
      .subscribe(result => console.log(result));
  }

}
