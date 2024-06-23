package de.mameie.databasemanager.sql.query.table.field;

import de.mameie.databasemanager.sql.query.table.constraints.ISqlConstraint;
import de.mameie.databasemanager.sql.query.table.datatypes.ISqlDatatype;

public class SqlFieldDefinitionWithConstrains implements ISqlFieldDefinition {

    private String fieldDefinitionWithConstraints;

    private SqlFieldDefinitionWithConstrains(String name, String dataType) {
        fieldDefinitionWithConstraints = String.format("%s %s", name, dataType);
    }

    public static SqlFieldDefinitionWithConstrains set(String name, ISqlDatatype dataType, ISqlConstraint constraint) {
        return new SqlFieldDefinitionWithConstrains(name, String.format("%s%s", dataType.getDatatype().toUpperCase(), constraint.getConstraint()));
    }


    @Override
    public String getFieldDefinition() {
        return fieldDefinitionWithConstraints;
    }
}
