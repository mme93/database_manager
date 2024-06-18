package de.mameie.databasemanager.sql.executor;

import de.mameie.databasemanager.sql.server.connection.DBServerConnectionFactory;
import de.mameie.databasemanager.util.check.exception.SqlMethodNotImplementedException;
import de.mameie.databasemanager.sql.query.ISqlQuery;
import de.mameie.databasemanager.sql.server.database.connection.DBConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractSqlExecutor implements ISqlExecutor {

    private String serverName;

    private String databaseName;

    private String tableName;

    private String STATUS;

    private final String SERVER = "SERVER";

    private final String TABLE = "TABLE";

    private final String DATABASE = "DATABASE";

    public AbstractSqlExecutor(String serverName) {
        STATUS = SERVER;
        this.serverName = serverName;
    }

    public AbstractSqlExecutor(String serverName, String databaseName) {
        STATUS = DATABASE;
        this.serverName = serverName;
        this.databaseName = databaseName;
    }

    public AbstractSqlExecutor(String serverName, String databaseName, String tableName) {
        STATUS = TABLE;
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    @Override
    public boolean drop(String name) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "DROP"));
    }

    @Override
    public final boolean create(String name) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "CREATE"));
    }

    @Override
    public Object show() {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "SHOW"));
    }

    @Override
    public final ResultSet executeQuery(ISqlQuery query) {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = createConnection(query);
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
            PreparedStatement statement = createConnection(query);
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
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeUpdate"));
    }

    @Override
    public final int[] executeBatch(List<ISqlQuery> query) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeBatch"));
    }

    @Override
    public ResultSet executeQuery(ISqlQuery query, SqlPrepStmtIndex index) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeQuery"));
    }

    @Override
    public boolean execute(ISqlQuery query, SqlPrepStmtIndex index) {
            throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "execute"));
    }

    @Override
    public boolean hasResult(ISqlQuery query, SqlPrepStmtIndex index) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "hasResult"));
    }

    @Override
    public int executeUpdate(ISqlQuery query, SqlPrepStmtIndex index) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeUpdate"));
    }

    @Override
    public int[] executeBatch(List<ISqlQuery> query, SqlPrepStmtIndex index) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeBatch"));
    }

    @Override
    public ResultSet executeQuery(ISqlQuery query, SqlPrepStmtParamName paramName) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeQuery"));
    }
    @Override
    public boolean execute(ISqlQuery query, SqlPrepStmtParamName paramName) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "execute"));
    }

    @Override
    public boolean hasResult(ISqlQuery query, SqlPrepStmtParamName paramName) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "hasResult"));
    }

    @Override
    public int executeUpdate(ISqlQuery query, SqlPrepStmtParamName paramName) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeUpdate"));
    }

    @Override
    public int[] executeBatch(List<ISqlQuery> query, SqlPrepStmtParamName paramName) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeBatch"));
    }

    private PreparedStatement createConnection(ISqlQuery query) throws SQLException {
        Connection con = switch (STATUS) {
            case SERVER -> DBServerConnectionFactory.getInstance(serverName).getConnection();
            case TABLE, DATABASE -> DBConnectionFactory.getInstance(serverName, databaseName).getConnection();
            default -> throw new RuntimeException(String.format("Status with input %s was not found."));
        };
        return con.prepareStatement(query.toSql());
    }
}
