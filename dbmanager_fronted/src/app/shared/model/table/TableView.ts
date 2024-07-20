export interface DatabaseTableView {
  tableName?: string;
  rowSize?: number;
  colSize?: number;
  metaData?: TableMetadata[];
  databaseTableRows?: DatabaseTableRow[];
}

export interface TableMetadata {
  field?: string;
  type?: string;
  nullable?: string;
  key?: string;
  defaultValue?: string;
}

export interface DatabaseTableRow {
  index?: number;
  databaseTableCells: DatabaseTableCell[];
}

export interface DatabaseTableCell {
  value?: string;
}
