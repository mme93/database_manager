import {Component, OnInit} from '@angular/core';
import {DialogService, DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";

@Component({
  selector: 'app-table-metadata-dialog',
  templateUrl: './table-metadata-dialog.component.html',
  styleUrl: './table-metadata-dialog.component.scss'
})
export class TableMetadataDialogComponent implements OnInit {

  constructor(public ref: DynamicDialogRef, private dialogService: DialogService, private config: DynamicDialogConfig) {
  }

  ngOnInit(): void {
    console.log(this.config.data.metaData);
  }

}
