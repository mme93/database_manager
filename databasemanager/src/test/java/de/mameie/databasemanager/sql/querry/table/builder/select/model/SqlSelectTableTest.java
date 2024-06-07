package de.mameie.databasemanager.sql.querry.table.builder.select.model;

import de.mameie.databasemanager.sql.querry.table.builder.update.SqlUpdateTable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SqlSelectTableTest {

    @Test
    void sqlSelect() {


       String query= SqlUpdateTable.table("CAR").where("PS > 200").toSql();

        System.out.println(query);
    }
}
