package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;

public class TableViewSqlExecutor extends AbstractSqlExecutor {

    public TableViewSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
    }
}
