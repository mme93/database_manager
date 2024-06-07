package de.mameie.databasemanager.sql.executor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SqlPrepareStatementWithIndex {

    public SqlPrepareStatementWithIndex(int index, String parameter) {
        this.index = index;
        this.parameter = parameter;
    }

    public SqlPrepareStatementWithIndex(String parameter, String parameterName) {
        this.parameter = parameter;
        this.parameterName = parameterName;
    }

    private int index;
    private String parameter;
    private String parameterName;

}
