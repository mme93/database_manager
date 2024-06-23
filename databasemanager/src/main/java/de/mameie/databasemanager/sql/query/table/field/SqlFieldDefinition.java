package de.mameie.databasemanager.sql.query.table.field;

public class SqlFieldDefinition implements ISqlFieldDefinition {

    private String fieldDefinition;


    private SqlFieldDefinition(String name, String dataType) {
        fieldDefinition = String.format("%s %s", name, dataType);
    }

    public static SqlFieldDefinition set(String name, String dataType) {
        return new SqlFieldDefinition(name, dataType);
    }


    @Override
    public String getFieldDefinition() {
        return fieldDefinition;
    }
}
