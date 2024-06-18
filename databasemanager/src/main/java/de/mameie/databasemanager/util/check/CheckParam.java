package de.mameie.databasemanager.util.check;

import de.mameie.databasemanager.util.check.exception.ParamException;

/**
 * Utility class for parameter validation.
 */
public class CheckParam {

    /**
     * Checks if the specified object is not null.
     *
     * @param obj the object to check
     * @param objName the name of the object, used in the exception message if the object is null
     * @return the original object if it is not null
     * @throws ParamException if the object is null
     */
    public static Object isNotNull(Object obj, String objName) {
        if (obj == null) {
            throw new ParamException(String.format("Param with the name %s is null.", objName));
        }
        return obj;
    }

}
