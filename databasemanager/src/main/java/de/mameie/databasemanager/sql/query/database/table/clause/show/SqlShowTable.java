package de.mameie.databasemanager.sql.query.database.table.clause.show;

import de.mameie.databasemanager.sql.query.ISqlQuery;

public class SqlShowTable implements ISqlQuery {

    private String value;

    private SqlShowTable(String value) {
        this.value = value;
    }

    public static SqlShowTable show() {
        return new SqlShowTable("TABLES");
    }

    @Override
    public String toSql() {
        return String.format("SHOW %s", value);
    }

    @Override
    public String getAction() {
        return "SHOW";
    }
}
