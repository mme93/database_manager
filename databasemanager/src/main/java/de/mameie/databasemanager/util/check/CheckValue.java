package de.mameie.databasemanager.util.check;

import de.mameie.databasemanager.util.check.exception.ParamException;
import org.springframework.stereotype.Service;

@Service
public class CheckValue {

    public Object checkValue(Object object, String valueName) {
        if (object != null) {
            return object;
        }
        throw new ParamException(String.format("No value set (null) with name %s.", valueName));
    }

}
