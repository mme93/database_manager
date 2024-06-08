package de.mameie.databasemanager.sql.query.table.builder.delete;

import de.mameie.databasemanager.sql.query.ISqlQuery;

public class SqlDeleteTable implements ISqlQuery {

    private String tableName;
    private String condition;

    public SqlDeleteTable from(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public SqlDeleteTable where(String condition) {
        this.condition = condition;
        return this;
    }

    public ISqlQuery build() {
      return this;
    }

    @Override
    public String toSql() {
        StringBuilder query = new StringBuilder("DELETE FROM ");
        query.append(tableName);
        if (condition != null) {
            query.append(" WHERE ").append(condition);
        }
        return query.toString();
    }

    @Override
    public String getAction() {
        return null;
    }
}
