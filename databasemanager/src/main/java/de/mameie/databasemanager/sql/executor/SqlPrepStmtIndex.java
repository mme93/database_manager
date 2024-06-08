package de.mameie.databasemanager.sql.executor;

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
