package de.mameie.databasemanager.sql.select;

import de.mameie.databasemanager.sql.ISqlQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlSelect implements ISqlQuery {
    public static final String WILDCARD = "*";
    private String tableName;
    private List<String> columns = new ArrayList<>();
    private String condition;

    private SqlSelect(){}

    public static SqlSelect builder(){
        return new SqlSelect();
    }
    public SqlSelect from(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public SqlSelect select(String... columns) {
        this.columns.addAll(Arrays.asList(columns));
        return this;
    }

    public SqlSelect where(String condition) {
        this.condition = condition;
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
