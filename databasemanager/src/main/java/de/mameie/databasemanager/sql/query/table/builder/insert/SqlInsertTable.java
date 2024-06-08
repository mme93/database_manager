package de.mameie.databasemanager.sql.query.table.builder.insert;

import de.mameie.databasemanager.sql.query.ISqlQuery;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlInsertTable implements ISqlQuery {

    private String tableName;
    private Map<String, Object> values = new HashMap<>();

    public SqlInsertTable into(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public SqlInsertTable setValue(String column, Object value) {
        values.put(column, value);
        return this;
    }

    public ISqlQuery build() {
        return this;
    }

    @Override
    public String toSql() {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(tableName).append(" (");
        query.append(String.join(", ", values.keySet()));
        query.append(") VALUES (");
        query.append(values.values().stream().map(Object::toString).collect(Collectors.joining(", ")));
        query.append(")");
        return query.toString();
    }

    @Override
    public String getAction() {
        return "INSERT";
    }
}
