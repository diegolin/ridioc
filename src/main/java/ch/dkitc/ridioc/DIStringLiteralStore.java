package ch.dkitc.ridioc;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DIStringLiteralStore {

    private final Map<String, String> singleValueMap = new HashMap<String, String>();
    private final Map<String, String[]> arrayValueMap = new HashMap<String, String[]>();
    private final Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap;

    public DIStringLiteralStore(Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap) {
        this.wrappedPrimitiveTypeMap = wrappedPrimitiveTypeMap;
    }

    public Object convertSingleValueTo(String name, Class<?> type) {
        String value = mustBeInSingleValueMap(name);
        if (Number.class.isAssignableFrom(type)) {
            try {
                return type.getMethod("valueOf", String.class).invoke(/* static */ null, value);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert '" + name + "'='" + value + "' to number type '" + type.getName() + "'", ex);
            }
        } else if (type.isPrimitive()) {
            Class<?> wrappedType = wrappedPrimitiveTypeMap.get(type);
            if (wrappedType == null) {
                throw new IllegalArgumentException("Cannot convert '" + name + "'='" + value + "' to primitive type '" + type.getName() + "' no wrapped type found");
            }
            try {
                return wrappedType.getMethod("valueOf", String.class).invoke(/* static */ null, value);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert '" + name + "'='" + value + "' to primitive type '" + type.getName() + "' with wrapped type '" + wrappedType + "'", ex);
            }
        } else if (type.isEnum()) {
            try {
                return type.getMethod("valueOf", Class.class, String.class).invoke(/* static */ null, type, value);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert '" + name + "'='" + value + "' to enum type '" + type.getName() + "'", ex);
            }
        } else if (String.class.isAssignableFrom(type)) {
            return value;
        } else if (Date.class.isAssignableFrom(type)) {
            return DatatypeConverter.parseDateTime(value).getTime();
        } else if (Boolean.class.isAssignableFrom(type)) {
            return Boolean.valueOf(value);
        } else if (Character.class.isAssignableFrom(type)) {
            // we support unicode, e.g. "\u0048"
            if (!value.startsWith("\\u")) {
                throw new IllegalArgumentException("Unicode character must start with '\\u' but looks like this: '" + value + "'");
            }
            String unicode = value.substring(2);
            return Character.valueOf((char)Integer.parseInt(unicode, 16));
        } else {
            throw new IllegalArgumentException(type + " is not supported");
        }
    }

    public boolean containsSingleValue(String name) {
        return singleValueMap.containsKey(name);
    }

    public String putSingleValue(String name, String singleValue) {
        return singleValueMap.put(name, singleValue);
    }

    public String[] getArrayValue(String name) {
        return mustBeInArrayValueMap(name);
    }

    public String[] putArrayValue(String name, String[] arrayValue) {
        return arrayValueMap.put(name, arrayValue);
    }

    private String mustBeInSingleValueMap(String name) {
        if (!singleValueMap.containsKey(name)) {
            throw new IllegalArgumentException("String literal Store does not contain single value for name '" + name + "'");
        }
        return singleValueMap.get(name);
    }

    private String[] mustBeInArrayValueMap(String name) {
        if (!arrayValueMap.containsKey(name)) {
            throw new IllegalArgumentException("String literal store does not contain array value for name '" + name + "'");
        }
        return arrayValueMap.get(name);
    }
}
