package de.mameie.databasemanager.sql.parameter;

public class SqlParameterWithValue implements ISqlParameter {

    private String parameter;

    private SqlParameterWithValue(String name, String value, String operator) {
        parameter = String.format("%s %s %s", name, operator, value);
    }

    private ISqlParameter set(String name, String value, String operator) {
        return new SqlParameterWithValue(name, value, operator);
    }

    @Override
    public String getParameter() {
        return parameter;
    }
}
