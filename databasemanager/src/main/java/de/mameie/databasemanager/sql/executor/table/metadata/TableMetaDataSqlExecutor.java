package de.mameie.databasemanager.sql.executor.table.metadata;

import de.mameie.databasemanager.sql.executor.table.TableSqlExecutor;

public class TableMetaDataSqlExecutor extends TableSqlExecutor {

    public TableMetaDataSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
    }
}
