package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;

public class TableSqlExecutor extends AbstractSqlExecutor {

    private String databaseName;

    public TableSqlExecutor(String serverName,String databaseName) {
        super(serverName);
        this.databaseName=databaseName;
    }



    public void changeDatabase(String databaseName){
        this.databaseName=databaseName;
    }

}
