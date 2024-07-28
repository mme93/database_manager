import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TableMetadataView} from "../../../../../shared/model/table/TableView";
import {
  CreateMetaDataTableElement,
  CreateMetaDataTableElementUi
} from "../../../../../shared/model/components/table/UiTables";
import {CreateTableUiService} from "../../../../../shared/service/ui/dashboard/table/create-table-ui.service";

@Component({
  selector: 'app-create-table',
  templateUrl: './create-table.component.html',
  styleUrl: './create-table.component.scss'
})
export class CreateTableComponent implements OnInit {
  tableMetaUi: CreateMetaDataTableElementUi | undefined;
  metaData: TableMetadataView[] = [];
  tableName = '';
  databaseName = '';
  searchTable = '';

  constructor(private route: ActivatedRoute, private createTableService: CreateTableUiService) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.tableMetaUi = this.createTableService.init(params['tableName'], params['databaseName']);
      this.tableName = params['tableName'];

      this.databaseName = params['databaseName'];
    });
  }

  addMetaData() {
    if (this.tableMetaUi) {
      let size = this.tableMetaUi?.metaDataTableElements.length;
      this.tableMetaUi?.metaDataTableElements.push({
        nr: size + 1,
        isSelected: false,
        field: '',
        type: this.tableMetaUi?.typeDropDown[0],
        typeInfo: '',
        nullable: this.tableMetaUi?.nullableDropDown[0],
        key: this.tableMetaUi?.keyDropDown[0],
        defaultValue: ''
      });
    }
  }

  deleteRows() {
    if (this.tableMetaUi) {
      let updatedMetaData: CreateMetaDataTableElement[] = [];
      let index = 1;
      for (let i = 0; i < this.tableMetaUi.metaDataTableElements.length; i++) {
        if (!this.tableMetaUi.metaDataTableElements[i].isSelected) {
          this.tableMetaUi.metaDataTableElements[i].nr = index;
          updatedMetaData.push(this.tableMetaUi.metaDataTableElements[i]);
          index++;
        }
      }
      this.tableMetaUi.metaDataTableElements = updatedMetaData;
    }
  }

  createTable() {
    console.log(this.tableMetaUi?.metaDataTableElements)
  }

  loadMetaData(searchTable: string) {

  }
}
