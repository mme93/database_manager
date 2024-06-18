package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.query.database.SqlDatabaseClause;
import de.mameie.databasemanager.sql.query.table.clause.create.SqlCreateTable;
import de.mameie.databasemanager.sql.query.table.clause.select.SqlSelectTable;

import java.sql.ResultSet;
import java.util.List;

public class TableSqlExecutor extends AbstractSqlExecutor {

    private String databaseName;
    private String tableName;

    public TableSqlExecutor(String serverName, String databaseName) {
        super(serverName, databaseName);
        this.databaseName = databaseName;
    }

    public TableSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    public void alter() {

    }

    @Override
    public boolean drop(String tableName) {
        return super.execute(
                SqlDatabaseClause
                        .drop()
                        .database()
                        .name(databaseName)
                        .build()
        );
    }

    public boolean createTable(String tableName, String x) {
        return super.execute(
                SqlCreateTable
                        .create()
                        .tableName(tableName)
                        .addColumn(x)
                        .build()
        );

    }

    @Override
    public Object show() {
        ResultSet resultSet = super.executeQuery(
                SqlSelectTable
                        .builder()
                        .select(SqlSelectTable.WILDCARD)
                        .from(tableName)
                        .build()
        );
        return resultSet;
    }
}
