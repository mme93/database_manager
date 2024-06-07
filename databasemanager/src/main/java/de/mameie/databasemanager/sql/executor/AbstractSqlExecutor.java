package de.mameie.databasemanager.sql.executor;

import de.mameie.databasemanager.sql.connection.DatabaseConnectionSingleton;
import de.mameie.databasemanager.sql.connection.DatabaseServerSettings;
import de.mameie.databasemanager.sql.querry.ISqlQuery;

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
    public final ResultSet executeQuery(ISqlQuery query) {
        ResultSet resultSet = null;
        try {
            Connection con = DatabaseConnectionSingleton.getInstance(DatabaseServerSettings.CLOUD_XXL).getConnection();
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
            Connection con = DatabaseConnectionSingleton.getInstance(DatabaseServerSettings.CLOUD_XXL).getConnection();
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

}
