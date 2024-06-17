package de.mameie.databasemanager.sql.server.database.connection;

import de.mameie.databasemanager.sql.server.connection.DBServerSettings;
import de.mameie.databasemanager.util.helper.StaticPropertyHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBConnectionFactory {

    private static List<DBConnectionFactory> instances = new ArrayList<>();

    private String tableUrl;

    private String serverName;

    private Connection con;

    public DBConnectionFactory(String serverName, String tableUrl, Connection con) {
        this.tableUrl = tableUrl;
        this.serverName = serverName;
        this.con = con;
    }

    public static synchronized DBConnectionFactory getInstance(String serverName, String databaseName, String tableName) throws SQLException {
        DBConnectionFactory instance;

        String tableUrl = String.format("%s/%s", databaseName, tableName);

        Optional<DBConnectionFactory> opt = instances.
                stream().
                filter(databaseConnection ->
                        databaseConnection.serverName.equals(serverName) &&
                                databaseConnection.tableUrl.equals(tableUrl)
                ).findAny();

        if (!opt.isPresent()) {
            System.out.println("No connection found, create new connection.");
            return createNewDatabaseConnectionSingleton(serverName, tableUrl);
        }
        instance = opt.get();

        if (instance.getConnection().isClosed()) {
            System.out.println("Connection found, but was closed, create new connection.");
            instances.remove(instance);
            return createNewDatabaseConnectionSingleton(serverName, tableUrl);
        }
        System.out.println("Found existing connection.");
        return instance;
    }

    private static DBConnectionFactory createNewDatabaseConnectionSingleton(String serverName, String tableUrl) throws SQLException {
        DBConnectionFactory instance;
        instance = new DBConnectionFactory(serverName, tableUrl, createConnection(serverName, tableUrl));
        instances.add(instance);
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

    private static Connection createConnection(String serverName, String tableUrl) throws SQLException {
        if (serverName.equals(DBServerSettings.CLOUD_SERVER)) {
            return DriverManager.getConnection(
                    StaticPropertyHolder.getStaticNameForCloudServerIp() + tableUrl,
                    StaticPropertyHolder.getStaticNameForCloudServerUserName(),
                    StaticPropertyHolder.getStaticNameForCloudServerPassword()
            );
        } else if (serverName.equals(DBServerSettings.CLOUD_XXL)) {
            return DriverManager.getConnection(
                    StaticPropertyHolder.getStaticNameForCloudXxlIp() + tableUrl,
                    StaticPropertyHolder.getStaticNameForCloudXxlUserName(),
                    StaticPropertyHolder.getStaticNameForCloudServerPassword()
            );
        }
        throw new RuntimeException(String.format("Can`t find server name: %s.", serverName));
    }

    public void closeConnection() throws SQLException {
        if (!this.con.isClosed()) {
            this.con.close();
        }
    }

    public String getServerName() {
        return serverName;
    }
}

