package de.mameie.databasemanager.util.check;

import de.mameie.databasemanager.util.check.exception.ParamException;

public class CheckParam {

    public static Object isNull(Object obj, String objName) {
        if (obj == null) {
            throw new ParamException(String.format("Value with the name %s is null.", objName));
        }
        return obj;
    }

}
