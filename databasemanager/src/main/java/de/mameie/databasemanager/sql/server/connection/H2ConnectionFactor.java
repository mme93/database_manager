package de.mameie.databasemanager.sql.server.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionFactor {

    private final static String JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;MODE=MySQL;";
    private final static String USER = "sa";
    private final static String PASS = "";

    private static H2ConnectionFactor h2ConnectionFactor;

    private Connection con;

    private H2ConnectionFactor(Connection con) {
        this.con = con;
    }

    public static synchronized H2ConnectionFactor getInstance() throws SQLException {
        if (h2ConnectionFactor == null || h2ConnectionFactor.getConnection().isClosed()) {
            System.out.println("No connection found/open, create new connection.");
            createNewDatabaseConnectionSingleton();
        }
        return h2ConnectionFactor;
    }

    private static void createNewDatabaseConnectionSingleton() {
        try {
            Class.forName(JDBC_DRIVER);
            h2ConnectionFactor = new H2ConnectionFactor(DriverManager.getConnection(DB_URL, USER, PASS));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
