package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.server.connection.DBServerSettings;
import de.mameie.databasemanager.sql.server.connection.H2ConnectionFactory;
import de.mameie.databasemanager.sql.server.database.table.model.DatabaseTableView;
import de.mameie.databasemanager.util.check.exception.ParamException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

import static java.util.Arrays.asList;

@SpringBootTest
public class TableViewSqlExecutorTest {

    private final String SERVER_NAME = "SERVER_NAME";
    private final String DATABASE_NAME = "DATABASE_NAME";
    private final String TABLE_NAME = "users_table";

    @BeforeAll
    static void setUp() throws SQLException {
        System.out.println("Setup!");
        Connection connection = H2ConnectionFactory.getInstance().getConnection();
        PreparedStatement schemaStatement = connection.prepareStatement("create table users_table (id INT,first_name VARCHAR(50),last_name VARCHAR(50),email VARCHAR(50),gender VARCHAR(50),ip_address VARCHAR(20),domain_name VARCHAR(50))");
        schemaStatement.execute();
        schemaStatement.close();
        for(String query:asList(
                "insert into users_table (id, first_name, last_name, email, gender, ip_address, domain_name) values (1, 'Beau', 'Rigler', 'brigler0@vistaprint.com', 'Male', '69.23.120.242', '360.cn')",
                "insert into users_table (id, first_name, last_name, email, gender, ip_address, domain_name) values (2, 'Chandler', 'Chadwyck', 'cchadwyck1@paginegialle.it', 'Male', '209.170.53.69', 'scientificamerican.com')",
                "insert into users_table (id, first_name, last_name, email, gender, ip_address, domain_name) values (3, 'Jorry', 'Thornham', 'jthornham2@sohu.com', 'Female', '168.246.45.177', 'netvibes.com')"
        )){
            PreparedStatement dataStatement = connection.prepareStatement(query);
            dataStatement.execute();
            dataStatement.close();
        }
        connection.close();
    }

    @AfterAll
    static void clear() throws SQLException {
        System.out.println("Clear!");
        Connection connection = H2ConnectionFactory.getInstance().getConnection();
        PreparedStatement schemaStatement = connection.prepareStatement("drop table users_table");
        schemaStatement.execute();
        schemaStatement.close();
        connection.close();
    }

    @Test
    void testIfExceptionThrowByNullServerNameInConstructor() {
        Assertions.assertThrows(
                ParamException.class,
                () -> TableViewSqlExecutor
                        .builder()
                        .withServerName(null)
                        .withDatabaseName(DATABASE_NAME)
                        .withTableName(TABLE_NAME)
                        .build(),
                "Param with the name serverName is null."
        );
    }

    @Test
    void testIfExceptionThrowByNullDatabaseNameInConstructor() {
        Assertions.assertThrows(
                ParamException.class,
                () -> TableViewSqlExecutor
                        .builder()
                        .withServerName(SERVER_NAME)
                        .withDatabaseName(null)
                        .withTableName(TABLE_NAME)
                        .build(),
                "Param with the name databaseName is null."
        );
    }

    @Test
    void testIfExceptionThrowByNullTableNameInConstructor() {
        Assertions.assertThrows(
                ParamException.class,
                () -> TableViewSqlExecutor
                        .builder()
                        .withServerName(SERVER_NAME)
                        .withDatabaseName(DATABASE_NAME)
                        .withTableName(null)
                        .build(),
                "Param with the name tableName is null."
        );
    }

    @Test
    void testTableViewIsFill() throws SQLException {

        TableViewSqlExecutor executor = TableViewSqlExecutor
                .builder()
                .withServerName(DBServerSettings.CLOUD_XXL)
                .withDatabaseName(DATABASE_NAME)
                .withTableName(TABLE_NAME)
                .build();
        executor.changeStatus("TEST");
        DatabaseTableView view = executor.generateTableView();

        Assertions.assertEquals(view.getColSize(),7);
        Assertions.assertEquals(view.getRowSize(),3);
        Assertions.assertEquals(view.getDatabaseTableRows().size(),3);
        Assertions.assertEquals(view.getDatabaseTableRows().get(0).getDatabaseTableCells().size(),7);
    }

}
