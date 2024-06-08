package de.mameie.databasemanager.sql.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SqlClause implements ISqlQuery {

    private String action;
    private String destination;
    private String destinationName;

    private SqlClause(String action) {
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

        private SqlClause sqlClause;

        public SqlClauseBuilder(String action) {
            sqlClause = new SqlClause(action);
        }

        public SqlClauseBuilder database() {
            sqlClause.setDestination("DATABASE");
            return this;
        }

        public SqlClauseBuilder table() {
            sqlClause.setDestination("TABLE");
            return this;
        }

        public SqlClauseBuilder databases() {
            sqlClause.setDestination("DATABASES");
            return this;
        }

        public SqlClauseBuilder tables() {
            sqlClause.setDestination("TABLES");
            return this;
        }

        public SqlClauseBuilder name(String name) {
            sqlClause.setDestinationName(name);
            return this;
        }

        public ISqlQuery build() {
            return sqlClause;
        }

    }

}
