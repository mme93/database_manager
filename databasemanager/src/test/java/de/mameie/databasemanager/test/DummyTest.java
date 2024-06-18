package de.mameie.databasemanager.test;

import de.mameie.databasemanager.sql.executor.table.TableViewSqlExecutor;
import de.mameie.databasemanager.sql.query.table.clause.describe.SqlDescribeTable;
import de.mameie.databasemanager.sql.server.connection.DBServerSettings;
import de.mameie.databasemanager.sql.server.database.connection.DBConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
public class DummyTest {


    @Test
    void test() throws SQLException {
        TableViewSqlExecutor tableViewSqlExecutor = TableViewSqlExecutor
                .builder()
                .withServerName(DBServerSettings.CLOUD_XXL)
                .withDatabaseName("ms_learning")
                .withTableName("account").build();

        Connection con = DBConnectionFactory.getInstance(DBServerSettings.CLOUD_XXL, "ms_learning").getConnection();
        PreparedStatement statement = con.prepareStatement(SqlDescribeTable.builder().describe("account").build().toSql());
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            System.out.println("Field: " + set.getString("Field"));
            System.out.println("Type: " + set.getString("Type"));
            System.out.println("Null: " + set.getString("Null"));
            System.out.println("Key: " + set.getString("Key"));
            System.out.println("Default: " + set.getString("Default"));
            System.out.println("Extra: " + set.getString("Extra"));
            System.out.println("<--------------------------------------------->");
        }
        con.close();
    }
}
