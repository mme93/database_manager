package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.query.database.SqlDatabaseClause;
import de.mameie.databasemanager.sql.query.table.clause.create.SqlCreateTable;
import de.mameie.databasemanager.sql.query.table.clause.show.SqlShowTable;
import de.mameie.databasemanager.sql.query.table.field.ISqlFieldDefinition;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableSqlExecutor extends AbstractSqlExecutor {

    private String databaseName;
    private String tableName;
    private String serverName;

    private TableSqlExecutor(String serverName, String databaseName) {
        super(serverName, databaseName);
        this.serverName = serverName;
        this.databaseName = databaseName;
    }

    private TableSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    public void alter() {

    }

    public boolean drop(String tableName) {
        return super.execute(
                SqlDatabaseClause
                        .drop()
                        .table()
                        .name(tableName)
                        .build()
        );
    }

    public boolean checkTableExists(String tableName) throws SQLException {
        Connection con = super.createConnection();
        DatabaseMetaData metaData = con.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    public List<String> getAllTableNames() throws SQLException {
        List<String> tableNames = new ArrayList<>();
        ResultSet resultSet = super.executeQuery(SqlShowTable.show());
        while (resultSet.next()) {
            tableNames.add(resultSet.getString(1));
        }
        return tableNames;
    }

    public boolean createTable(List<ISqlFieldDefinition> fieldDefinitionList) {
        return super.execute(
                SqlCreateTable
                        .create()
                        .tableName(tableName)
                        .addColumns(fieldDefinitionList)
                        .build()
        );

    }

    public static TableSqlExecutorBuilder builder() {
        return new TableSqlExecutorBuilder();
    }

    public static class TableSqlExecutorBuilder {

        private String serverName;
        private String databaseName;
        private String tableName;

        public TableSqlExecutorBuilder withServerName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        public TableSqlExecutorBuilder withDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        public TableSqlExecutorBuilder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public TableSqlExecutor build() {
            if (tableName != null) {
                return new TableSqlExecutor(serverName, databaseName, tableName);
            }
            return new TableSqlExecutor(serverName, databaseName);
        }
    }
}
