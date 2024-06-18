package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.util.check.exception.ParamException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TableViewSqlExecutorTest {

    private final String SERVER_NAME="SERVER_NAME";
    private final String DATABASE_NAME="DATABASE_NAME";
    private final String TABLE_NAME="TABLE_NAME";

    @Test
    void testIfExceptionThrowByNullServerNameInConstructor(){
        Assertions.assertThrows(
                ParamException.class,
                ()->TableViewSqlExecutor
                        .builder()
                        .withServerName(null)
                        .withDatabaseName(DATABASE_NAME)
                        .withTableName(TABLE_NAME)
                        .build(),
                "Param with the name serverName is null."
        );
    }

    @Test
    void testIfExceptionThrowByNullDatabaseNameInConstructor(){
        Assertions.assertThrows(
                ParamException.class,
                ()->TableViewSqlExecutor
                        .builder()
                        .withServerName(SERVER_NAME)
                        .withDatabaseName(null)
                        .withTableName(TABLE_NAME)
                        .build(),
                "Param with the name databaseName is null."
        );
    }

    @Test
    void testIfExceptionThrowByNullTableNameInConstructor(){
        Assertions.assertThrows(
                ParamException.class,
                ()->TableViewSqlExecutor
                        .builder()
                        .withServerName(SERVER_NAME)
                        .withDatabaseName(DATABASE_NAME)
                        .withTableName(null)
                        .build(),
                "Param with the name tableName is null."
        );
    }



}
