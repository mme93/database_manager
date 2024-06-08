package de.mameie.databasemanager.sql.database.service;

import de.mameie.databasemanager.sql.connection.DatabaseServerSettings;
import de.mameie.databasemanager.sql.executor.database.DatabaseSqlExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    public boolean deleteDatabase(String databaseName) {
        DatabaseSqlExecutor executor = new DatabaseSqlExecutor(DatabaseServerSettings.CLOUD_XXL);
        return executor.drop(databaseName);
    }

    public boolean createDatabase(String databaseName) {
        DatabaseSqlExecutor executor = new DatabaseSqlExecutor(DatabaseServerSettings.CLOUD_XXL);
        return executor.create(databaseName);
    }

    public List<String> getDatabaseNames() {
        DatabaseSqlExecutor executor = new DatabaseSqlExecutor(DatabaseServerSettings.CLOUD_XXL);
        return  executor.show();
    }
}
