package de.mameie.databasemanager.sql.executor;

import de.mameie.databasemanager.sql.querry.ISqlQuery;

import java.sql.ResultSet;
import java.util.List;

public interface ISqlExecutor {
    ResultSet executeQuery(ISqlQuery query);

    boolean execute(ISqlQuery query);

    boolean hasResult(ISqlQuery query);

    int executeUpdate(ISqlQuery query);

    int[] executeBatch(List<ISqlQuery> query);

    ResultSet executeQuery(ISqlQuery query, SqlPrepareStatementWithIndex prepareStatementWithIndex);

    boolean execute(ISqlQuery query, SqlPrepareStatementWithIndex prepareStatementWithIndex);

    boolean hasResult(ISqlQuery query, SqlPrepareStatementWithIndex prepareStatementWithIndex);

    int executeUpdate(ISqlQuery query, SqlPrepareStatementWithIndex prepareStatementWithIndex);

    int[] executeBatch(List<ISqlQuery> query, SqlPrepareStatementWithIndex prepareStatementWithIndex);

    ResultSet executeQuery(ISqlQuery query, SqlPrepareStatementWithParameterName prepareStatementWithParameterName);

    boolean execute(ISqlQuery query, SqlPrepareStatementWithParameterName prepareStatementWithParameterName);

    boolean hasResult(ISqlQuery query, SqlPrepareStatementWithParameterName prepareStatementWithParameterName);

    int executeUpdate(ISqlQuery query, SqlPrepareStatementWithParameterName prepareStatementWithParameterName);

    int[] executeBatch(List<ISqlQuery> query, SqlPrepareStatementWithParameterName prepareStatementWithParameterName);


}
