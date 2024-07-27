import {Component, OnInit} from '@angular/core';
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {TableMetadata} from "../../../model/table/TableView";
import {PaginatorState} from "primeng/paginator";

@Component({
  selector: 'app-table-metadata-dialog',
  templateUrl: './table-metadata-dialog.component.html',
  styleUrl: './table-metadata-dialog.component.scss'
})
export class TableMetadataDialogComponent implements OnInit {
  page: number = 0;
  rows: number = 5;
  columnNames: string[] = ['Name', 'Type', 'Is nullable', 'Key', 'Default Value', 'Delete','Edit']
  metaData: TableMetadata[] = [];
  filteredMetaData: TableMetadata[] = [];

  constructor(public ref: DynamicDialogRef, private dialogService: DialogService, private config: DynamicDialogConfig) {
  }

  ngOnInit(): void {
    this.metaData = this.config.data.metaData;
    this.filteredMetaData = this.config.data.metaData;
    this.updatePage();
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
}
