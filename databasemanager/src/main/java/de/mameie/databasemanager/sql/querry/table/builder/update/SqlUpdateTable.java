package de.mameie.databasemanager.sql.querry.table.builder.update;

import de.mameie.databasemanager.sql.querry.ISqlQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlUpdateTable implements ISqlQuery {

    private String tableName;
    private Map<String, Object> values = new HashMap<>();
    private String condition;

    private SqlUpdateTable(String tableName){
        this.tableName = tableName;
    }

    public static SqlUpdateTable table(String tableName) {
        return new SqlUpdateTable(tableName);
    }

    public SqlUpdateTable set(String column, Object value) {
        values.put(column, value);
        return this;
    }

    public SqlUpdateTable where(String condition) {
        this.condition = condition;
        return this;
    }

    public ISqlQuery build() {
        return this;
    }

    @Override
    public String toSql() {
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(tableName).append(" SET ");
        List<String> setClauses = new ArrayList<>();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            setClauses.add(entry.getKey() + " = " + entry.getValue());
        }
        query.append(String.join(", ", setClauses));
        if (condition != null) {
            query.append(" WHERE ").append(condition);
        }
        return query.toString();
    }

    @Override
    public String getAction() {
        return "UPDATE";
    }
}
