package de.mameie.databasemanager.sql.query.table.clause.create;

import de.mameie.databasemanager.sql.query.ISqlQuery;

import java.util.ArrayList;
import java.util.List;

public class SqlCreateTable implements ISqlQuery {

    private String tableName;
    private List<String> columns = new ArrayList<>();

    private SqlCreateTable() {}

    public static SqlCreateTableBuilder builder() {
        return new SqlCreateTableBuilder();
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getColumns() {
        return columns;
    }

    @Override
    public String toSql() {
        return String.format("CREATE TABLE %s (%s);", tableName, String.join(", ", columns));
    }

    @Override
    public String getAction() {
        return "CREATE";
    }

    @Override
    public String toString() {
        return String.format("CREATE TABLE %s (%s);", tableName, String.join(", ", columns));
    }

    public static class SqlCreateTableBuilder {
        private SqlCreateTable sqlCreateTable = new SqlCreateTable();

        public SqlCreateTableBuilder tableName(String tableName) {
            sqlCreateTable.tableName = tableName;
            return this;
        }

        public SqlCreateTableBuilder addColumn(String columnDefinition) {
            sqlCreateTable.columns.add(columnDefinition);
            return this;
        }

        public SqlCreateTable build() {
            return sqlCreateTable;
        }
    }


}
