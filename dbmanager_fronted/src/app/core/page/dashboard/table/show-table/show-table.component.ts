import {Component, OnInit} from '@angular/core';
import {TableService} from "../../../../../shared/service/http/table/table.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-show-table',
  templateUrl: './show-table.component.html',
  styleUrl: './show-table.component.scss'
})
export class ShowTableComponent implements OnInit {

  tableName: string | null = '';
  databaseName: string | null = '';

  constructor(private tableService: TableService,private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.tableService.getTableByNameAndDatabase(localStorage.getItem('server'), params['databaseName'], params['tableName'])
        .subscribe(result => console.log(result));
    });
  }

}
