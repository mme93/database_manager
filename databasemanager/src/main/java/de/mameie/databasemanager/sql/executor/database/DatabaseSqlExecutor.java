package de.mameie.databasemanager.sql.executor.database;

import de.mameie.databasemanager.sql.server.connection.ConnectionFactory;
import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.query.ISqlQuery;
import de.mameie.databasemanager.sql.query.database.SqlDatabaseClause;
import lombok.Getter;
import lombok.Setter;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class DatabaseSqlExecutor extends AbstractSqlExecutor {

    private String serverName;
    private ISqlQuery query;


    public DatabaseSqlExecutor(String serverName) {
        super(serverName);
        this.serverName = serverName;
    }

    @Override
    public boolean drop(String databaseName) {
        return super.execute(
                SqlDatabaseClause
                        .drop()
                        .database()
                        .name(databaseName)
                        .build()
        );
    }

    @Override
    public boolean create(String databaseName) {
        return super.execute(
                SqlDatabaseClause
                        .create()
                        .database()
                        .name(databaseName)
                        .build()
        );
    }

    @Override
    public List<String> show() {
        List<String>databaseNames= new ArrayList<>();
        try {
            DatabaseMetaData metaData = ConnectionFactory.getInstance(serverName).getConnection().getMetaData();
            ResultSet resultSet = metaData.getCatalogs();

            while (resultSet.next()) {
                String databaseName = resultSet.getString("TABLE_CAT");
                databaseNames.add(databaseName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseNames;
    }
}
