package de.mameie.databasemanager.sql.server.database.table.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DatabaseColumnMetadata {

    private String field;
    private String type;
    private String nullable;
    private String key;
    private String defaultValue;

}
