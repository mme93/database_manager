package de.mameie.databasemanager.sql.query.table.clause.describe;

import de.mameie.databasemanager.sql.query.ISqlQuery;

public class SqlDescribeTable implements ISqlQuery {

    private String tableName;

    private SqlDescribeTable() {
    }

    public static SqlDescribeTable builder() {
        return new SqlDescribeTable();
    }

    public SqlDescribeTable describe(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public ISqlQuery build() {
        return this;
    }

    @Override
    public String toSql() {
        return String.format("SHOW COLUMNS FROM %s",tableName);
    }

    @Override
    public String getAction() {
        return "DESCRIBE";
    }
}
