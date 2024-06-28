package de.mameie.databasemanager.sql.executor.table.view;

import de.mameie.databasemanager.sql.executor.table.TableSqlExecutor;
import de.mameie.databasemanager.sql.query.table.clause.select.SqlSelectTable;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableCell;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableRow;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableView;
import de.mameie.databasemanager.sql.server.database.table.model.view.TableMetadata;
import de.mameie.databasemanager.util.check.CheckParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableViewSqlExecutor extends TableSqlExecutor {

    private String databaseName;
    private String tableName;
    private String serverName;

    private TableViewSqlExecutor(String serverName, String databaseName) {
        super(serverName, databaseName);
        CheckParam.isNotNull(serverName,"serverName");
        CheckParam.isNotNull(databaseName,"databaseName");
        this.serverName=serverName;
        this.databaseName=databaseName;
    }

    private TableViewSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
        CheckParam.isNotNull(serverName,"serverName");
        CheckParam.isNotNull(databaseName,"databaseName");
        CheckParam.isNotNull(tableName,"tableName");
        this.serverName=serverName;
        this.databaseName=databaseName;
        this.tableName=tableName;
    }

    private List<DatabaseTableRow> getRow(List<TableMetadata> headers) {
        List<DatabaseTableRow> databaseTableRows = new ArrayList<>();
        ResultSet resultSet = this.executeQuery(SqlSelectTable.builder().select(SqlSelectTable.WILDCARD).from(tableName));
        try {
            int index = 1;
            while (resultSet.next()) {
                List<DatabaseTableCell> databaseTableCells = new ArrayList<>();
                for (TableMetadata header : headers) {
                    databaseTableCells.add(new DatabaseTableCell(resultSet.getString(header.getField())));
                }
                databaseTableRows.add(new DatabaseTableRow(index, databaseTableCells));
                index++;
            }
            return databaseTableRows;
        } catch (SQLException e) {
            throw new RuntimeException("Can´t read the value.", e);
        }
    }

    public DatabaseTableView generateTableView() {
        List<TableMetadata> metadata = super.getMetaData();
        List<DatabaseTableRow> rows = getRow(metadata);
        return DatabaseTableView
                .builder()
                .withTableName(tableName)
                .withMetaData(metadata)
                .withRows(rows)
                .build();
    }

    public static TableViewSqlExecutorBuilder builder(){
        return new TableViewSqlExecutorBuilder();
    }
    public static class TableViewSqlExecutorBuilder{
        private String serverName;
        private String databaseName;
        private String tableName;

        public TableViewSqlExecutorBuilder withServerName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        public TableViewSqlExecutorBuilder withDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        public TableViewSqlExecutorBuilder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public TableViewSqlExecutor build() {
            if (tableName != null) {
                return new TableViewSqlExecutor(serverName, databaseName, tableName);
            }
            return new TableViewSqlExecutor(serverName, databaseName);
        }
    }

}
