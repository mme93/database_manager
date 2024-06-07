package de.mameie.databasemanager.sql.connection;

import de.mameie.databasemanager.util.helper.StaticPropertyHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DatabaseConnectionSingleton {


    private static List<DatabaseConnectionSingleton> instances = new ArrayList<>();

    private String serverName;

    private Connection con;

    private DatabaseConnectionSingleton(String serverName, Connection con) {
        this.con = con;
        this.serverName = serverName;
    }


    public static synchronized DatabaseConnectionSingleton getInstance(String serverName) throws SQLException {

        DatabaseConnectionSingleton instance;

        Optional<DatabaseConnectionSingleton> opt = instances.
                stream().
                filter(databaseConnection -> databaseConnection.serverName.equals(serverName)).findAny();

        if (!opt.isPresent()) {
            System.out.println("No connection found, create new connection.");
            return createNewDatabaseConnectionSingleton(serverName);
        }
        instance = opt.get();

        if (instance.getConnection().isClosed()) {
            System.out.println("Connection found, but was closed, create new connection.");
            instances.remove(instance);
            return createNewDatabaseConnectionSingleton(serverName);
        }
        System.out.println("Found existing connection.");
        return instance;
    }

    private static DatabaseConnectionSingleton createNewDatabaseConnectionSingleton(String serverName) throws SQLException {
        DatabaseConnectionSingleton instance;
        instance = new DatabaseConnectionSingleton(serverName, createConnection(serverName));
        instances.add(instance);
        return instance;
    }

    private Connection getConnection() {
        return con;
    }

    private static Connection createConnection(String serverName) throws SQLException {
        if (serverName.equals(DatabaseServerSettings.CLOUD_SERVER)) {
            return DriverManager.getConnection(
                    StaticPropertyHolder.getStaticNameForCloudServerIp(),
                    StaticPropertyHolder.getStaticNameForCloudServerUserName(),
                    StaticPropertyHolder.getStaticNameForCloudServerPassword()
            );
        } else if (serverName.equals(DatabaseServerSettings.CLOUD_XXL)) {
            return DriverManager.getConnection(
                    StaticPropertyHolder.getStaticNameForCloudXxlIp(),
                    StaticPropertyHolder.getStaticNameForCloudXxlUserName(),
                    StaticPropertyHolder.getStaticNameForCloudServerPassword()
            );
        }
        throw new RuntimeException(String.format("Can`t find server name: %s.", serverName));
    }

    public String getServerName() {
        return serverName;
    }
}

