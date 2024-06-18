package de.mameie.databasemanager.sql.executor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SqlPrepStmtIndex {
    private int index;
    private String parameter;
}
