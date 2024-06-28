package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.query.ISqlQuery;
import de.mameie.databasemanager.sql.query.database.SqlDatabaseClause;
import de.mameie.databasemanager.sql.query.table.clause.create.SqlCreateTable;
import de.mameie.databasemanager.sql.query.table.clause.describe.SqlDescribeTable;
import de.mameie.databasemanager.sql.query.table.clause.show.SqlShowTable;
import de.mameie.databasemanager.sql.query.table.field.ISqlFieldDefinition;
import de.mameie.databasemanager.sql.server.database.table.model.view.TableMetadata;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableSqlExecutor extends AbstractSqlExecutor implements ITableSqlExecutor {

    private String databaseName;
    private String tableName;
    private String serverName;

    public TableSqlExecutor(String serverName, String databaseName) {
        super(serverName, databaseName);
        this.serverName = serverName;
        this.databaseName = databaseName;
    }

    public TableSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }


    @Override
    public boolean drop() {
        return super.execute(
                SqlDatabaseClause
                        .drop()
                        .table()
                        .name(tableName)
                        .build()
        );
    }

    @Override
    public boolean drop(String tableName) {
        return super.execute(
                SqlDatabaseClause
                        .drop()
                        .table()
                        .name(tableName)
                        .build()
        );
    }

    @Override
    public boolean exist() throws SQLException {
        Connection con = super.createConnection();
        DatabaseMetaData metaData = con.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    @Override
    public boolean exist(String tableName) throws SQLException {
        Connection con = super.createConnection();
        DatabaseMetaData metaData = con.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    @Override
    public boolean createTable(List<ISqlFieldDefinition> fieldDefinitionList) {
        return super.execute(
                SqlCreateTable
                        .create()
                        .tableName(tableName)
                        .addColumns(fieldDefinitionList)
                        .build()
        );
    }

    @Override
    public List<TableMetadata> getMetaData() {
        List<TableMetadata> tableMetadata = new ArrayList<>();
        ResultSet resultSet = this.executeQuery(SqlDescribeTable.builder().describe(tableName).build());
        try {
            while (resultSet.next()) {
                tableMetadata.add(new TableMetadata(
                        resultSet.getString("Field"),
                        resultSet.getString("Type"),
                        resultSet.getString("Null"),
                        resultSet.getString("Key"),
                        resultSet.getString("Default")
                ));
            }
            return tableMetadata;
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can´t read the column information header form table: %s.", tableName), e);
        }
    }

    @Override
    public List<String> getTableNames() throws SQLException {
        List<String> tableNames = new ArrayList<>();
        ResultSet resultSet = super.executeQuery(SqlShowTable.show());
        while (resultSet.next()) {
            tableNames.add(resultSet.getString(1));
        }
        return tableNames;
    }
}
