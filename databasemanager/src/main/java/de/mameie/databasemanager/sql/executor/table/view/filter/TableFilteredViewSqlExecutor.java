package de.mameie.databasemanager.sql.executor.table.view.filter;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.query.table.clause.describe.SqlDescribeTable;
import de.mameie.databasemanager.sql.query.table.clause.select.SqlSelectTable;
import de.mameie.databasemanager.sql.query.table.condition.ISqlCondition;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseColumnMetadata;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableCell;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableRow;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableView;
import de.mameie.databasemanager.util.check.CheckParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TableFilteredViewSqlExecutor extends AbstractSqlExecutor {

    private String serverName;
    private String databaseName;
    private String tableName;
    private ISqlCondition condition;
    private List<String> columns;

    private TableFilteredViewSqlExecutor(String serverName, String databaseName, String tableName, ISqlCondition condition, List<String> columns) {
        super(serverName, databaseName, tableName);
        CheckParam.isNotNull(serverName, "serverName");
        CheckParam.isNotNull(databaseName, "databaseName");
        CheckParam.isNotNull(tableName, "tableName");
        CheckParam.isNotEmpty(columns, "columns");
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.columns = columns;
        this.condition = condition;
    }

    public DatabaseTableView generateTableView() {
        List<DatabaseColumnMetadata> metadata = getColMetaData();
        List<DatabaseTableRow> rows = getRow(metadata);
        return DatabaseTableView
                .builder()
                .withTableName(tableName)
                .withMetaData(metadata)
                .withRows(rows)
                .build();
    }

    private List<DatabaseTableRow> getRow(List<DatabaseColumnMetadata> headers) {
        List<DatabaseTableRow> databaseTableRows = new ArrayList<>();
        List<String> selectedColumns = new ArrayList<>();
        headers.stream().forEach(header -> selectedColumns.add(header.getField()));

        ResultSet resultSet = this.executeQuery(SqlSelectTable.builder().select(selectedColumns).from(tableName));
        try {
            int index = 1;
            while (resultSet.next()) {
                List<DatabaseTableCell> databaseTableCells = new ArrayList<>();
                for (DatabaseColumnMetadata header : headers) {
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

    private List<DatabaseColumnMetadata> getColMetaData() {
        List<DatabaseColumnMetadata> databaseColumnMetadata = new ArrayList<>();
        ResultSet resultSet = this.executeQuery(SqlDescribeTable.builder().describe(tableName).build());
        try {
            while (resultSet.next()) {
                String columnField = resultSet.getString("Field");
                if (columns.stream().filter(column -> column.equals(columnField)).findAny().isPresent()) {
                    databaseColumnMetadata.add(new DatabaseColumnMetadata(
                            columnField,
                            resultSet.getString("Type"),
                            resultSet.getString("Null"),
                            resultSet.getString("Key"),
                            resultSet.getString("Default")
                    ));
                }
            }
            return databaseColumnMetadata;
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can´t read the column information header form table: %s.", tableName), e);
        }

    }

    public static TableFilteredViewSqlExecutorBuilder builder() {
        return new TableFilteredViewSqlExecutorBuilder();
    }

    public static class TableFilteredViewSqlExecutorBuilder {

        private String serverName;
        private String databaseName;
        private String tableName;
        private ISqlCondition condition;
        private List<String> columns;


        public TableFilteredViewSqlExecutorBuilder withServerName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        public TableFilteredViewSqlExecutorBuilder withDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        public TableFilteredViewSqlExecutorBuilder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public TableFilteredViewSqlExecutorBuilder withColumnAsWildCard() {
            columns.add("*");
            return this;
        }

        public TableFilteredViewSqlExecutorBuilder withColumns(List<String> columns) {
            this.columns.addAll(columns);
            return this;
        }

        public TableFilteredViewSqlExecutorBuilder withColumn(String column) {
            columns.add(column);
            return this;
        }

        public TableFilteredViewSqlExecutorBuilder withFilter(ISqlCondition condition) {
            this.condition = condition;
            return this;
        }

        public TableFilteredViewSqlExecutor build() {
            return new TableFilteredViewSqlExecutor(serverName, databaseName, tableName, condition, columns);
        }
    }

}
