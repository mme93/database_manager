package de.mameie.databasemanager.sql.server.database.table.service;

import de.mameie.databasemanager.sql.executor.table.TableSqlExecutor;
import de.mameie.databasemanager.sql.executor.table.TableViewSqlExecutor;
import de.mameie.databasemanager.sql.query.table.field.ISqlFieldDefinition;
import de.mameie.databasemanager.sql.server.database.table.model.DatabaseTableView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SqlTableService {

    public List<String> getAllTableNames(String serverName, String database) {
        List<String> tableNames = new ArrayList<>();

        return tableNames;
    }

    public DatabaseTableView getTableByName(String serverName, String database, String tableName) {
        return TableViewSqlExecutor
                .builder()
                .withServerName(serverName)
                .withDatabaseName(database)
                .withTableName(tableName)
                .build()
                .generateTableView();
    }

    public boolean createTable(String serverName, String database, String tableName, List<ISqlFieldDefinition> fieldDefinitionList) {
        TableSqlExecutor executor = TableSqlExecutor
                .builder()
                .withServerName(serverName)
                .withDatabaseName(database)
                .withTableName(tableName)
                .build();
        return executor.createTable(tableName, fieldDefinitionList);
    }
}
