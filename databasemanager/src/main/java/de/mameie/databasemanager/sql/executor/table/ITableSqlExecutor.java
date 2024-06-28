package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.query.table.field.ISqlFieldDefinition;
import de.mameie.databasemanager.sql.server.database.table.model.view.TableMetadata;

import java.sql.SQLException;
import java.util.List;

public interface ITableSqlExecutor {

    boolean drop();
    boolean drop(String tableName);

    boolean exist() throws SQLException;
    boolean exist(String tableName) throws SQLException ;

    boolean createTable(List<ISqlFieldDefinition> fieldDefinitionList);

    List<TableMetadata>getMetaData();
    List<String>getTableNames() throws SQLException;


}
