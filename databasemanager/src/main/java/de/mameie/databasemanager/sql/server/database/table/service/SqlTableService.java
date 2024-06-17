package de.mameie.databasemanager.sql.server.database.table.service;

import de.mameie.databasemanager.sql.executor.table.TableSqlExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SqlTableService {

    public List<String> getAllTableNames(String serverName, String database) {
        List<String>tableNames= new ArrayList<>();

        return tableNames;
    }

    public void getTableByName(String serverName, String database, String tableName) {
        TableSqlExecutor executor = new TableSqlExecutor(serverName,database,tableName);
    }

    public void createTable(String serverName, String database, String tableName) {
        TableSqlExecutor executor = new TableSqlExecutor(serverName,database);
        executor.create(tableName);
    }
}
