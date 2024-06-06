package de.mameie.databasemanager.util.test;

import de.mameie.databasemanager.sql.select.SqlSelect;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/")
    public String getTest() {
        return SqlSelect
                .builder()
                .from("CAR")
                .select(SqlSelect.WILDCARD)
                .build().toSql();
    }

}
