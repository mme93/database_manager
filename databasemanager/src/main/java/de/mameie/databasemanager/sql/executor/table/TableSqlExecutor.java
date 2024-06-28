package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.query.database.SqlDatabaseClause;
import de.mameie.databasemanager.sql.query.table.clause.create.SqlCreateTable;
import de.mameie.databasemanager.sql.query.table.clause.describe.SqlDescribeTable;
import de.mameie.databasemanager.sql.query.table.clause.show.SqlShowTable;
import de.mameie.databasemanager.sql.query.table.field.ISqlFieldDefinition;
import de.mameie.databasemanager.sql.server.database.table.model.view.TableMetadata;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TableSqlExecutor} class provides implementations for executing SQL queries
 * related to tables. It extends {@code AbstractSqlExecutor} and implements {@code ITableSqlExecutor}.
 * This class includes methods for creating, dropping, checking existence, and retrieving metadata of tables.
 */
public class TableSqlExecutor extends AbstractSqlExecutor implements ITableSqlExecutor {

    private String databaseName;
    private String tableName;
    private String serverName;

    /**
     * Constructs a {@code TableSqlExecutor} with the specified server name and database name.
     *
     * @param serverName   the name of the server.
     * @param databaseName the name of the database.
     */
    public TableSqlExecutor(String serverName, String databaseName) {
        super(serverName, databaseName);
        this.serverName = serverName;
        this.databaseName = databaseName;
    }

    /**
     * Constructs a {@code TableSqlExecutor} with the specified server name, database name, and table name.
     *
     * @param serverName   the name of the server.
     * @param databaseName the name of the database.
     * @param tableName    the name of the table.
     */
    public TableSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    /**
     * Drops the table associated with this executor.
     *
     * @return {@code true} if the table was successfully dropped, {@code false} otherwise.
     */
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

    /**
     * Drops the specified table.
     *
     * @param tableName the name of the table to drop.
     * @return {@code true} if the table was successfully dropped, {@code false} otherwise.
     */
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

    /**
     * Checks if the table associated with this executor exists.
     *
     * @return {@code true} if the table exists, {@code false} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean exist() throws SQLException {
        Connection con = super.createConnection();
        DatabaseMetaData metaData = con.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    /**
     * Checks if the specified table exists.
     *
     * @param tableName the name of the table to check.
     * @return {@code true} if the table exists, {@code false} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean exist(String tableName) throws SQLException {
        Connection con = super.createConnection();
        DatabaseMetaData metaData = con.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    /**
     * Creates a table with the specified field definitions.
     *
     * @param fieldDefinitionList a list of field definitions for the table.
     * @return {@code true} if the table was successfully created, {@code false} otherwise.
     */
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

    /**
     * Retrieves metadata for the table associated with this executor.
     *
     * @return a list of {@code TableMetadata} objects representing the table's metadata.
     */
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
            throw new RuntimeException(String.format("Can't read the column information header from table: %s.", tableName), e);
        }
    }

    /**
     * Retrieves the names of all tables in the database.
     *
     * @return a list of table names.
     * @throws SQLException if a database access error occurs.
     */
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
