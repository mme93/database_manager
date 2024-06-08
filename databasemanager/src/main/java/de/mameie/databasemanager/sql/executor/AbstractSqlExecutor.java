package de.mameie.databasemanager.sql.executor;

import de.mameie.databasemanager.sql.server.connection.ConnectionFactory;
import de.mameie.databasemanager.sql.server.connection.DatabaseServerSettings;
import de.mameie.databasemanager.sql.exception.SqlMethodNotImplementedException;
import de.mameie.databasemanager.sql.query.ISqlQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractSqlExecutor implements ISqlExecutor {

    private String serverName;

    public AbstractSqlExecutor(String serverName) {
        this.serverName = serverName;
    }


    @Override
    public boolean drop(String name) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.","DROP"));
    }

    @Override
    public boolean create(String name) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.","CREATE"));
    }

    @Override
    public List<String> show() {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.","FIND"));
    }

    @Override
    public final ResultSet executeQuery(ISqlQuery query) {
        ResultSet resultSet = null;
        try {
            Connection con = ConnectionFactory.getInstance(serverName).getConnection();
            PreparedStatement statement = con.prepareStatement(query.toSql());
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public final boolean execute(ISqlQuery query) {
        Boolean result = false;
        try {
            Connection con = ConnectionFactory.getInstance(DatabaseServerSettings.CLOUD_XXL).getConnection();
            PreparedStatement statement = con.prepareStatement(query.toSql());
            result = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean hasResult(ISqlQuery query) {
        return execute(query);
    }

    @Override
    public final int executeUpdate(ISqlQuery query) {
        return 0;
    }

    @Override
    public final int[] executeBatch(List<ISqlQuery> query) {
        return new int[0];
    }

    @Override
    public ResultSet executeQuery(ISqlQuery query, SqlPrepStmtIndex index) {
        return null;
    }

    @Override
    public boolean execute(ISqlQuery query, SqlPrepStmtIndex index) {
        return false;
    }

    @Override
    public boolean hasResult(ISqlQuery query, SqlPrepStmtIndex index) {
        return false;
    }

    @Override
    public int executeUpdate(ISqlQuery query, SqlPrepStmtIndex index) {
        return 0;
    }

    @Override
    public int[] executeBatch(List<ISqlQuery> query, SqlPrepStmtIndex index) {
        return new int[0];
    }

    @Override
    public ResultSet executeQuery(ISqlQuery query, SqlPrepStmtParamName paramName) {
        return null;
    }

    @Override
    public boolean execute(ISqlQuery query, SqlPrepStmtParamName paramName) {
        return false;
    }

    @Override
    public boolean hasResult(ISqlQuery query, SqlPrepStmtParamName paramName) {
        return false;
    }

    @Override
    public int executeUpdate(ISqlQuery query, SqlPrepStmtParamName paramName) {
        return 0;
    }

    @Override
    public int[] executeBatch(List<ISqlQuery> query, SqlPrepStmtParamName paramName) {
        return new int[0];
    }
}
