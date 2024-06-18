package de.mameie.databasemanager.sql.executor;

import de.mameie.databasemanager.sql.executor.model.SqlPrepStmtIndex;
import de.mameie.databasemanager.sql.executor.model.SqlPrepStmtParamName;
import de.mameie.databasemanager.sql.query.ISqlQuery;

import java.sql.ResultSet;
import java.util.List;

public interface ISqlExecutor {

    boolean drop(String name);

    boolean create(String name);

    Object show();

    ResultSet executeQuery(ISqlQuery query);

    boolean execute(ISqlQuery query);

    boolean hasResult(ISqlQuery query);

    int executeUpdate(ISqlQuery query);

    int[] executeBatch(List<ISqlQuery> query);

    ResultSet executeQuery(ISqlQuery query, SqlPrepStmtIndex index);

    boolean execute(ISqlQuery query, SqlPrepStmtIndex index);

    boolean hasResult(ISqlQuery query, SqlPrepStmtIndex index);

    int executeUpdate(ISqlQuery query, SqlPrepStmtIndex index);

    int[] executeBatch(List<ISqlQuery> query, SqlPrepStmtIndex index);

    ResultSet executeQuery(ISqlQuery query, SqlPrepStmtParamName paramName);

    boolean execute(ISqlQuery query, SqlPrepStmtParamName paramName);

    boolean hasResult(ISqlQuery query, SqlPrepStmtParamName paramName);

    int executeUpdate(ISqlQuery query, SqlPrepStmtParamName paramName);

    int[] executeBatch(List<ISqlQuery> query, SqlPrepStmtParamName paramName);


}
