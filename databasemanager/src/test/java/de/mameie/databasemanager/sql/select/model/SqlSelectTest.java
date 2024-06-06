package de.mameie.databasemanager.sql.select.model;

import de.mameie.databasemanager.sql.ISqlQuery;
import de.mameie.databasemanager.sql.select.SqlSelect;
import de.mameie.databasemanager.sql.update.SqlUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SqlSelectTest {

    @Test
    void sqlSelect() {
        ISqlQuery query = SqlSelect
                .builder()
                .select("TIRE","PS","COLOR","BRAND")
                .from("CAR")
                .where("PS > 50")
                .build();

       query=SqlUpdate.table("CAR").where("PS > 200");
        System.out.println(query.toSql());
    }
}
