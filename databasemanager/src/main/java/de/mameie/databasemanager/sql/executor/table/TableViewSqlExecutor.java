package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.query.table.clause.describe.SqlDescribeTable;
import de.mameie.databasemanager.sql.query.table.clause.select.SqlSelectTable;
import de.mameie.databasemanager.sql.server.database.table.model.DatabaseColumnMetadata;
import de.mameie.databasemanager.sql.server.database.table.model.DatabaseTableCell;
import de.mameie.databasemanager.sql.server.database.table.model.DatabaseTableRow;
import de.mameie.databasemanager.sql.server.database.table.model.DatabaseTableView;
import de.mameie.databasemanager.util.check.CheckParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableViewSqlExecutor extends AbstractSqlExecutor {

    private String serverName;
    private String databaseName;
    private String tableName;

    private TableViewSqlExecutor(TableViewSqlExecutorBuilder builder) {
        super(builder.serverName, builder.databaseName, builder.tableName);
        CheckParam.isNotNull(builder, "builder");
        CheckParam.isNotNull(builder.serverName, "serverName");
        CheckParam.isNotNull(builder.databaseName, "databaseName");
        CheckParam.isNotNull(builder.tableName, "tableName");
        this.serverName = builder.serverName;
        this.databaseName = builder.databaseName;
        this.tableName = builder.tableName;
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
        ResultSet resultSet = this.executeQuery(SqlSelectTable.builder().select(SqlSelectTable.WILDCARD).from(tableName));
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
                databaseColumnMetadata.add(new DatabaseColumnMetadata(
                        resultSet.getString("Field"),
                        resultSet.getString("Type"),
                        resultSet.getString("Null"),
                        resultSet.getString("Key"),
                        resultSet.getString("Default")
                ));
            }
            return databaseColumnMetadata;
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can´t read the column information header form table: %s.", tableName), e);
        }

    }

    public static TableViewSqlExecutorBuilder builder() {
        return new TableViewSqlExecutorBuilder();
    }

    public static class TableViewSqlExecutorBuilder {
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
            return new TableViewSqlExecutor(this);
        }
    }
}
