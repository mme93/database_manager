package de.mameie.databasemanager.sql.query.table.clause.select.model;

import de.mameie.databasemanager.sql.server.connection.DBServerSettings;
import de.mameie.databasemanager.sql.executor.database.DatabaseSqlExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SqlSelectTableTest {

    @Test
    void sqlSelect() {
        DatabaseSqlExecutor executor = new DatabaseSqlExecutor(DBServerSettings.CLOUD_XXL);
        executor.show();
        System.out.println("<-------------->");
        executor.create("database_manager");
        executor.show();
        System.out.println("<-------------->");
        executor.drop("database_manager");
        executor.show();
        System.out.println("<-------------->");
    }
}
