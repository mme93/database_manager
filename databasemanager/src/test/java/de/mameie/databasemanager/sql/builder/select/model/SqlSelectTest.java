package de.mameie.databasemanager.sql.builder.select.model;

import de.mameie.databasemanager.sql.builder.update.SqlUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SqlSelectTest {

    @Test
    void sqlSelect() {


       String query=SqlUpdate.table("CAR").where("PS > 200").toSql();

        System.out.println(query);
    }
}
