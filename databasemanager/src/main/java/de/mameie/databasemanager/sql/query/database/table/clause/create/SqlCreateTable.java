package de.mameie.databasemanager.sql.query.database.table.clause.create;

import de.mameie.databasemanager.sql.query.ISqlQuery;
import de.mameie.databasemanager.sql.query.database.table.field.ISqlFieldDefinition;

import java.util.ArrayList;
import java.util.List;

public class SqlCreateTable implements ISqlQuery {

    private String tableName;
    private List<String> iSqlFieldDefinitions = new ArrayList<>();

    private SqlCreateTable() {
    }

    public static SqlCreateTableBuilder create() {
        return new SqlCreateTableBuilder();
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getColumns() {
        return iSqlFieldDefinitions;
    }

    @Override
    public String toSql() {
        return String.format("CREATE TABLE %s (%s);", tableName, String.join(", ", iSqlFieldDefinitions));
    }

    @Override
    public String getAction() {
        return "CREATE";
    }

    @Override
    public String toString() {
        return String.format("CREATE TABLE %s (%s);", tableName, String.join(", ", iSqlFieldDefinitions));
    }

    public static class SqlCreateTableBuilder {
        private SqlCreateTable sqlCreateTable = new SqlCreateTable();

        public SqlCreateTableBuilder tableName(String tableName) {
            sqlCreateTable.tableName = tableName;
            return this;
        }

        public SqlCreateTableBuilder addColumn(ISqlFieldDefinition iSqlFieldDefinition) {
            sqlCreateTable.iSqlFieldDefinitions.add(iSqlFieldDefinition.getFieldDefinition());
            return this;
        }

        public SqlCreateTableBuilder addColumns(List<ISqlFieldDefinition> iSqlFieldDefinitions) {
            iSqlFieldDefinitions.stream().forEach(iSqlFieldDefinition ->
                    sqlCreateTable.iSqlFieldDefinitions.add(iSqlFieldDefinition.getFieldDefinition())
                    );
            return this;
        }

        public SqlCreateTable build() {
            return sqlCreateTable;
        }
    }


}
