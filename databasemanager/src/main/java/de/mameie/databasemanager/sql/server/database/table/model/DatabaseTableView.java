package de.mameie.databasemanager.sql.server.database.table.model;

import lombok.Getter;

import java.util.List;

@Getter
public class DatabaseTableView {

    private String tableName;
    private int rowSize;
    private int colSize;
    private List<DatabaseColumnMetadata> metaData;
    private List<DatabaseTableRow> databaseTableRows;


    private DatabaseTableView() {
    }


    private void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    private void setColSize(int colSize) {
        this.colSize = colSize;
    }

    private void setHeaders(List<DatabaseColumnMetadata> metaData) {
        this.metaData = metaData;
    }

    private void setDatabaseTableRows(List<DatabaseTableRow> databaseTableRows) {
        this.databaseTableRows = databaseTableRows;
    }

    public static TableViewBuilder builder() {
        return new TableViewBuilder();
    }

    public static class TableViewBuilder {

        private DatabaseTableView databaseTableView = new DatabaseTableView();

        public TableViewBuilder withTableName(String tableName) {
            databaseTableView.setTableName(tableName);
            return this;
        }

        public TableViewBuilder withMetaData(List<DatabaseColumnMetadata> metaData) {
            databaseTableView.setHeaders(metaData);
            databaseTableView.setColSize(metaData.size());
            return this;
        }

        public TableViewBuilder withRows(List<DatabaseTableRow> databaseTableRows) {
            databaseTableView.setDatabaseTableRows(databaseTableRows);
            databaseTableView.setRowSize(databaseTableRows.size());
            return this;
        }

        public DatabaseTableView build() {
            return databaseTableView;
        }
    }
}
