import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TableMetadataView} from "../../../../../shared/model/table/TableView";
import {PaginatorState} from "primeng/paginator";

@Component({
  selector: 'app-create-table',
  templateUrl: './create-table.component.html',
  styleUrl: './create-table.component.scss'
})
export class CreateTableComponent implements OnInit {
  tableName = '';
  databaseName = '';
  page: number = 0;
  rows: number = 30;
  isEdit: boolean = false;
  columnNames: string[] = ['Nr', 'Selected', 'Name', 'Type', 'Is nullable', 'Key', 'Default Value']
  metaData: TableMetadataView[] = [];
  filteredMetaData: TableMetadataView[] = [];

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.tableName = params['tableName'];
      this.databaseName = params['databaseName'];
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
    this.filteredMetaData = this.metaData.slice(start, end);
  }

  addMetaData() {
    this.filteredMetaData.push({nr: this.metaData.length + 1});
    this.metaData.push({nr: this.metaData.length + 1});
  }

  deleteRows() {
    let updatedMetaData: TableMetadataView[] = [];
    let index = 1;
    for (let i = 0; i < this.metaData.length; i++) {
      if (!this.metaData[i].isSelected) {
        this.metaData[i].nr = index;
        updatedMetaData.push(this.metaData[i]);
        index++;
      }
    }
    this.metaData = updatedMetaData;
    this.updatePage();
  }
}
