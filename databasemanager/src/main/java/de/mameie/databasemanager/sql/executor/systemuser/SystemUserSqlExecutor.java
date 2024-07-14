package de.mameie.databasemanager.sql.executor.systemuser;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.executor.model.SystemUser;
import de.mameie.databasemanager.sql.executor.table.view.TableViewSqlExecutor;
import de.mameie.databasemanager.util.check.CheckParam;

import java.util.ArrayList;
import java.util.List;

public class SystemUserSqlExecutor extends AbstractSqlExecutor {

    public SystemUserSqlExecutor(String serverName, String databaseName) {
        super(serverName, databaseName);
        CheckParam.isNotNull(serverName,"serverName");
        CheckParam.isNotNull(databaseName,"databaseName");
    }

    public List<SystemUser> getAllSystemUser(){
        List<SystemUser>systemUsers= new ArrayList<>();

        return systemUsers;
    }

    public List<SystemUser> getAllGrantedSystemUserFromDatabase(){
        List<SystemUser>systemUsers= new ArrayList<>();

        return systemUsers;
    }

    public List<SystemUser> getAllGrantedSystemUserFromTable(){
        List<SystemUser>systemUsers= new ArrayList<>();

        return systemUsers;
    }

    public boolean isSystemUserGrantedToTable(){

        return true;
    }

    public boolean isSystemUserGrantedToDatabase(){

        return true;
    }

    public boolean grantedSystemUserToTable(){

        return true;
    }

    public boolean grantedSystemUserToDatabase(){

        return true;
    }

    public static SystemUserSqlExecutorBuilder builder() {
        return new SystemUserSqlExecutorBuilder();
    }

    public static class SystemUserSqlExecutorBuilder{
        private String serverName;
        private String databaseName;

        /**
         * Sets the server name for the builder.
         *
         * @param serverName the name of the server
         * @return the builder instance
         */
        public SystemUserSqlExecutorBuilder withServerName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        /**
         * Sets the database name for the builder.
         *
         * @param databaseName the name of the database
         * @return the builder instance
         */
        public SystemUserSqlExecutorBuilder withDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        /**
         * Builds and returns a {@code TableViewSqlExecutor} instance.
         *
         * @return a new {@code TableViewSqlExecutor} instance
         */
        public SystemUserSqlExecutor build() {
            return new SystemUserSqlExecutor(serverName, databaseName);
        }
    }
}
