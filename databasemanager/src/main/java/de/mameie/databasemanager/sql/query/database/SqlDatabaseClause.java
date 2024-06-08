package de.mameie.databasemanager.sql.query.database;

import de.mameie.databasemanager.sql.query.ISqlQuery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SqlDatabaseClause implements ISqlQuery {

    private String action;
    private String destination;
    private String destinationName;

    private SqlDatabaseClause(String action) {
        this.action = action;
    }

    public static SqlClauseBuilder drop() {
        return new SqlClauseBuilder("DROP");
    }

    public static SqlClauseBuilder create() {
        return new SqlClauseBuilder("CREATE");
    }

    public static SqlClauseBuilder show() {
        return new SqlClauseBuilder("SHOW");
    }


    @Override
    public String toSql() {
        if(destinationName != null){
            return String.format("%s %s %s", action, destination, destinationName);
        }
        return String.format("%s %s", action, destination);
    }

    @Override
    public String getAction() {
        return action;
    }

    public static class SqlClauseBuilder {

        private SqlDatabaseClause sqlDatabaseClause;

        public SqlClauseBuilder(String action) {
            sqlDatabaseClause = new SqlDatabaseClause(action);
        }

        public SqlClauseBuilder database() {
            sqlDatabaseClause.setDestination("DATABASE");
            return this;
        }

        public SqlClauseBuilder table() {
            sqlDatabaseClause.setDestination("TABLE");
            return this;
        }

        public SqlClauseBuilder databases() {
            sqlDatabaseClause.setDestination("DATABASES");
            return this;
        }

        public SqlClauseBuilder tables() {
            sqlDatabaseClause.setDestination("TABLES");
            return this;
        }

        public SqlClauseBuilder name(String name) {
            sqlDatabaseClause.setDestinationName(name);
            return this;
        }

        public ISqlQuery build() {
            return sqlDatabaseClause;
        }

    }

}
