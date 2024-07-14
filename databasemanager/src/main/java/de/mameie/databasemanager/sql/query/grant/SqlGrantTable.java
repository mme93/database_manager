package de.mameie.databasemanager.sql.query.grant;

import de.mameie.databasemanager.sql.query.ISqlQuery;

public class SqlGrantTable implements ISqlQuery {

    private String user;

    private String host;

    private SqlGrantTable() {
    }

    @Override
    public String toSql() {
        return String.format("SHOW GRANTS FOR '%s'@'%s';", user, host);
    }

    @Override
    public String getAction() {
        return "GRANT";
    }

    public static SqlGrantTable builder() {
        return new SqlGrantTable();
    }

    public SqlGrantTable withUser(String user) {
        this.user = user;
        return this;
    }

    public SqlGrantTable withHost(String host) {
        this.host = host;
        return this;
    }

    public ISqlQuery build() {
        return this;
    }
}
