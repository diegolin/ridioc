package ch.dkitc.ridioc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

public class DIUtils {

    private static Map<Class<?>, Class<?>> wrappedPrimitiveTypeMap;

    static {
        wrappedPrimitiveTypeMap = new HashMap<Class<?>, Class<?>>();
        wrappedPrimitiveTypeMap.put(byte.class, Byte.class);
        wrappedPrimitiveTypeMap.put(short.class, Short.class);
        wrappedPrimitiveTypeMap.put(int.class, Integer.class);
        wrappedPrimitiveTypeMap.put(long.class, Long.class);
        wrappedPrimitiveTypeMap.put(float.class, Float.class);
        wrappedPrimitiveTypeMap.put(double.class, Double.class);
        wrappedPrimitiveTypeMap.put(char.class, Character.class);
        wrappedPrimitiveTypeMap.put(boolean.class, Boolean.class);
    }

    public static Class<?> getWrappedPrimitiveType(Class<?> type) {
        if (!type.isPrimitive()) {
            throw new IllegalArgumentException("Given type '" + type + " is NOT primitive");
        }
        if (!wrappedPrimitiveTypeMap.containsKey(type)) {
            throw new IllegalArgumentException("There is no wrapped type for primitive type '" + type + "' available");
        }
        return wrappedPrimitiveTypeMap.get(type);
    }

    public static boolean hasDefaultConstructor(Class<?> type) {
        try {
            type.getDeclaredConstructor();
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static String checkName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Argument 'nanme' must NOT be null or empty");
        }
        return name;
    }

    public static <T> Class<T> checkType(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Argument 'type' must NOT be null");
        }
        return type;
    }

    public static <T> List<T> checkList(List<T> list, String argumentName) {
        if (list == null) {
            throw new IllegalArgumentException("Argument '" + argumentName + "' must NOT be null");
        }
        return list;
    }

    public static Object[] checkParams(Object[] params) throws IllegalArgumentException {
        if (params ==  null) {
            throw new IllegalArgumentException("'params' must not be null");
        }
        for (Object param : params) {
            if (param == null) {
                throw new IllegalArgumentException("Parameter within 'params' may NOT be null");
            }
        }
        return params;
    }

    public static void argumentsMustHaveExactArgumentsCountOf(Object[] args, int count) {
        if (args.length != count) {
            throw new IllegalArgumentException("There should be at EXACTLY " + count + " parameters, but there are " + args.length);
        }
    }

    public static void argumentsMustHaveAtLeastArgumentsCountOf(Object[] args, int count) {
        if (args.length < count) {
            throw new IllegalArgumentException("There should be at AT LEAST " + count + " parameters, but there are " + args.length);
        }
    }

    public static <T> T castTo(Object arg, Class<T> type) {
        if (type.isInstance(arg)) {
            return (T) arg;
        }
        throw new ClassCastException("Cannot cast value " + arg.getClass().getName() + " to type " + type.getName());
    }

    public static Object unboxToPrimitiveByteArray(Byte[] boxedPrimitiveArray) {
        return ArrayUtils.toPrimitive(boxedPrimitiveArray);
    }

    public static Object unboxToPrimitiveShortArray(Short[] boxedPrimitiveArray) {
        return ArrayUtils.toPrimitive(boxedPrimitiveArray);
    }

    public static Object unboxToPrimitiveIntegerArray(Integer[] boxedPrimitiveArray) {
        return ArrayUtils.toPrimitive(boxedPrimitiveArray);
    }

    public static Object unboxToPrimitiveLongArray(Long[] boxedPrimitiveArray) {
        return ArrayUtils.toPrimitive(boxedPrimitiveArray);
    }

    public static Object unboxToPrimitiveFloatArray(Float[] boxedPrimitiveArray) {
        return ArrayUtils.toPrimitive(boxedPrimitiveArray);
    }

    public static Object unboxToPrimitiveDoubleArray(Double[] boxedPrimitiveArray) {
        return ArrayUtils.toPrimitive(boxedPrimitiveArray);
    }

    public static Character[] convertToJavaLangCharacterArray(String[] unicodeCharacterArray) {
        List<Character> characters = new ArrayList<Character>();
        for (String unicodeCharacter : unicodeCharacterArray) {
            if (!unicodeCharacter.startsWith("\\u")) {
                throw new IllegalArgumentException("Unicode character must start with '\\u' but looks like this: '" + unicodeCharacter + "'");
            }
            String unicode = unicodeCharacter.substring(2);
            characters.add((char) Integer.parseInt(unicode, /* radix */ 16));
        }
        return characters.toArray(new Character[characters.size()]);
    }

    public static Object unboxToPrimitiveCharacterArray(String[] unicodeCharacterArray) {
        return ArrayUtils.toPrimitive(convertToJavaLangCharacterArray(unicodeCharacterArray));
    }

    public static Object unboxToPrimitiveCharacterArray(Character[] boxedPrimitiveArray) {
        return ArrayUtils.toPrimitive(boxedPrimitiveArray);
    }

    private DIUtils() {
    }

}
