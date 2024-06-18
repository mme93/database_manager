package de.mameie.databasemanager.sql.executor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SqlPrepStmtParamName {
    private String parameter;
    private String parameterName;
}
