package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.query.database.SqlDatabaseClause;

import java.util.List;

public class TableSqlExecutor extends AbstractSqlExecutor {

    private String databaseName;
    private String tableName;

    public TableSqlExecutor(String serverName,String databaseName) {
        super(serverName);
        this.databaseName=databaseName;
    }

    public TableSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName);
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    public void alter(){

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

    @Override
    public boolean create(String name) {
        return super.create(name);
    }

    @Override
    public List<String> show() {
        return super.show();
    }
}
