package de.mameie.databasemanager.sql.query.table.clause.select;

import de.mameie.databasemanager.sql.query.ISqlQuery;
import de.mameie.databasemanager.sql.query.table.condition.ISqlCondition;
import de.mameie.databasemanager.sql.query.table.condition.SqlWhereCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlSelectTable implements ISqlQuery {
    public static final String WILDCARD = "*";
    private String tableName;
    private List<String> columns = new ArrayList<>();
    private ISqlCondition condition;

    private SqlSelectTable() {
    }

    public static SqlSelectTable builder() {
        return new SqlSelectTable();
    }

    public SqlSelectTable from(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public SqlSelectTable select(String... columns) {
        this.columns.addAll(Arrays.asList(columns));
        return this;
    }

    public SqlSelectTable select(List<String> columns) {
        this.columns.addAll(columns);
        return this;
    }

    public SqlSelectTable where(String name, String operator, String value) {
        this.condition = SqlWhereCondition.set(name, operator, value);
        return this;
    }

    public ISqlQuery build() {
        return this;
    }

    @Override
    public String toSql() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append(columns.isEmpty() ? WILDCARD : String.join(",", columns));
        query.append(" FROM " + tableName);
        if (condition != null) {
            query.append(" WHERE " + condition);
        }
        return query.toString();
    }

    @Override
    public String getAction() {
        return "SELECT";
    }
}
