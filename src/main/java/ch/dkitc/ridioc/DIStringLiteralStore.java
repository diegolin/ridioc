package ch.dkitc.ridioc;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static ch.dkitc.ridioc.DIUtils.castTo;

public class DIStringLiteralStore {

    private final Map<String, Object> singleValueMap = new HashMap<String, Object>();
    private final Map<String, Object[]> arrayValueMap = new HashMap<String, Object[]>();
    private final Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap;

    public DIStringLiteralStore(Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap) {
        this.wrappedPrimitiveTypeMap = wrappedPrimitiveTypeMap;
    }

    public Object[] convertArrayValueTo(String name, Class<?> elementType)  {
        Object[] arrayValue = mustBeInArrayValueMap(name);
        Object [] typeArrayValue = castTo(Array.newInstance(elementType, arrayValue.length), Object[].class);
        for (int i=0; i<typeArrayValue.length; i++) {
            typeArrayValue[i] = internalConvertSingleValueTo(name, arrayValue[i], elementType);
        }
        return typeArrayValue;
    }

    public Object convertSingleValueTo(String name, Class<?> type) {
        Object singleValue = mustBeInSingleValueMap(name);
        return internalConvertSingleValueTo(name, singleValue, type);
    }

    public boolean containsSingleValue(String name) {
        return singleValueMap.containsKey(name);
    }

    public Object putSingleValue(String name, Object singleValue) {
        return singleValueMap.put(name, singleValue);
    }

    public boolean containsArrayValue(String name) {
        return arrayValueMap.containsKey(name);
    }

    public Object[] getArrayValue(String name) {
        return mustBeInArrayValueMap(name);
    }

    public Object[] putArrayValue(String name, Object[] arrayValue) {
        return arrayValueMap.put(name, arrayValue);
    }

    private Object internalConvertSingleValueTo(String name, Object singleValue, Class<?> type) {
        String singleValueAsString = String.valueOf(singleValue);
        if (Number.class.isAssignableFrom(type)) {
            DIConstructor diConstructor = new DIConstructors(type, wrappedPrimitiveTypeMap).findMatchingConstructorByParamTypes(String.class);
            if (diConstructor == null) {
                throw new IllegalArgumentException(type + " does not have constructor that takes a single string argument");
            }
            try {
                return diConstructor.newInstance(singleValueAsString);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert '" + name + "'='" + singleValueAsString + "' to type '" + type.getName() + "'", ex);
            }
        } else if (type.isPrimitive()) {
            Class<?> wrappedType = wrappedPrimitiveTypeMap.get(type);
            if (wrappedType == null) {
                throw new IllegalArgumentException("Cannot convert '" + name + "'='" + singleValueAsString + "' to primitive type '" + type.getName() + "' no wrapped type found");
            }
            try {
                return wrappedType.getMethod("valueOf", String.class).invoke(/* static */ null, singleValueAsString);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert '" + name + "'='" + singleValueAsString + "' to primitive type '" + type.getName() + "' with wrapped type '" + wrappedType + "'", ex);
            }
        } else if (type.isEnum()) {
            try {
                return type.getMethod("valueOf", Class.class, String.class).invoke(/* static */ null, type, singleValueAsString);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert '" + name + "'='" + singleValueAsString + "' to enum type '" + type.getName() + "'", ex);
            }
        } else if (String.class.isAssignableFrom(type)) {
            return String.valueOf(singleValueAsString);
        } else if (Date.class.isAssignableFrom(type)) {
            return DatatypeConverter.parseDateTime(singleValueAsString).getTime();
        } else if (Boolean.class.isAssignableFrom(type)) {
            return Boolean.valueOf(singleValueAsString);
        } else if (Character.class.isAssignableFrom(type)) {
            // we support unicode, e.g. "\u0048"
            if (!singleValue.toString().startsWith("\\u")) {
                throw new IllegalArgumentException("Unicode character must start with '\\u' but looks like this: '" + singleValueAsString + "'");
            }
            String unicode = singleValueAsString.substring(2);
            return (char) Integer.parseInt(unicode, /* radix */ 16);
        } else {
            throw new IllegalArgumentException(type + " is not supported");
        }
    }

    private Object mustBeInSingleValueMap(String name) {
        if (!singleValueMap.containsKey(name)) {
            throw new IllegalArgumentException("String literal Store does not contain single value for name '" + name + "'");
        }
        return singleValueMap.get(name);
    }

    private Object[] mustBeInArrayValueMap(String name) {
        if (!arrayValueMap.containsKey(name)) {
            throw new IllegalArgumentException("String literal store does not contain array value for name '" + name + "'");
        }
        return arrayValueMap.get(name);
    }
}
