package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.executor.SqlPrepareStatementWithIndex;
import de.mameie.databasemanager.sql.executor.SqlPrepareStatementWithParameterName;
import de.mameie.databasemanager.sql.querry.ISqlQuery;

import java.sql.ResultSet;
import java.util.List;

public class TableSqlExecutor extends AbstractSqlExecutor {

    private String databaseName;

    public TableSqlExecutor(String serverName,String databaseName) {
        super(serverName);
        this.databaseName=databaseName;
    }

    public void dropTable(String tableName){

    }

    public void createTable(String tableName){

    }

    public void findTable(String tableName){

    }

    public void changeDatabase(String databaseName){
        this.databaseName=databaseName;
    }

    @Override
    public ResultSet executeQuery(ISqlQuery query, SqlPrepareStatementWithIndex prepareStatementWithIndex) {
        return null;
    }

    @Override
    public boolean execute(ISqlQuery query, SqlPrepareStatementWithIndex prepareStatementWithIndex) {
        return false;
    }

    @Override
    public boolean hasResult(ISqlQuery query, SqlPrepareStatementWithIndex prepareStatementWithIndex) {
        return false;
    }

    @Override
    public int executeUpdate(ISqlQuery query, SqlPrepareStatementWithIndex prepareStatementWithIndex) {
        return 0;
    }

    @Override
    public int[] executeBatch(List<ISqlQuery> query, SqlPrepareStatementWithIndex prepareStatementWithIndex) {
        return new int[0];
    }

    @Override
    public ResultSet executeQuery(ISqlQuery query, SqlPrepareStatementWithParameterName prepareStatementWithParameterName) {
        return null;
    }

    @Override
    public boolean execute(ISqlQuery query, SqlPrepareStatementWithParameterName prepareStatementWithParameterName) {
        return false;
    }

    @Override
    public boolean hasResult(ISqlQuery query, SqlPrepareStatementWithParameterName prepareStatementWithParameterName) {
        return false;
    }

    @Override
    public int executeUpdate(ISqlQuery query, SqlPrepareStatementWithParameterName prepareStatementWithParameterName) {
        return 0;
    }

    @Override
    public int[] executeBatch(List<ISqlQuery> query, SqlPrepareStatementWithParameterName prepareStatementWithParameterName) {
        return new int[0];
    }
}
