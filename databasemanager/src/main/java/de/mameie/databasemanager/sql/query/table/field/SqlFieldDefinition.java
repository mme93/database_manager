package de.mameie.databasemanager.sql.query.table.field;

import de.mameie.databasemanager.sql.query.table.datatypes.ISqlDatatype;

public class SqlFieldDefinition implements ISqlFieldDefinition {

    private String fieldDefinition;


    private SqlFieldDefinition(String name, ISqlDatatype dataType) {
        fieldDefinition = String.format("%s %s", name, dataType.getDatatype());
    }

    public static SqlFieldDefinition set(String name, ISqlDatatype dataType) {
        return new SqlFieldDefinition(name, dataType);
    }


    @Override
    public String getFieldDefinition() {
        return fieldDefinition;
    }
}
