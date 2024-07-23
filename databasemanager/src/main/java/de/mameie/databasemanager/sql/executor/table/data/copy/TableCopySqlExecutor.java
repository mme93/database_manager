package de.mameie.databasemanager.sql.executor.table.data.copy;

import de.mameie.databasemanager.sql.executor.table.TableSqlExecutor;

public class TableCopySqlExecutor extends TableSqlExecutor {

    private String copyTableName;
    private String destinationDatabaseName;
    private String destinationServerName;


    private TableCopySqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
        destinationDatabaseName = databaseName;
        destinationServerName = serverName;
    }

    public void changeServer(String serverName){
        destinationServerName = serverName;
    }

    public void changeDatabase(String databaseName){
        destinationDatabaseName = databaseName;
    }

    public static TableCopySqlExecutorBuilder builder() {
        return new TableCopySqlExecutorBuilder();
    }

    public static class TableCopySqlExecutorBuilder{
        private String serverName;
        private String databaseName;
        private String tableName;

        /**
         * Sets the server name for the builder.
         *
         * @param serverName the name of the server
         * @return the builder instance
         */
        public TableCopySqlExecutorBuilder withServerName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        /**
         * Sets the database name for the builder.
         *
         * @param databaseName the name of the database
         * @return the builder instance
         */
        public TableCopySqlExecutorBuilder withDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        /**
         * Sets the table name for the builder.
         *
         * @param tableName the name of the table
         * @return the builder instance
         */
        public TableCopySqlExecutorBuilder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        /**
         * Builds and returns a {@code TableViewSqlExecutor} instance.
         *
         * @return a new {@code TableViewSqlExecutor} instance
         */
        public TableCopySqlExecutor build() {
            return new TableCopySqlExecutor(serverName, databaseName, tableName);
        }
    }

}
