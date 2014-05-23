package ch.dkitc.ridioc;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class DIUtils {

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

    public static Object unboxToPrimitive(Object[] boxedPrimitiveArray, Class<?> primitiveArrayType) {
        try {
            return ArrayUtils.class.getMethod("toPrimitive", boxedPrimitiveArray.getClass()).invoke(null, primitiveArrayType.cast(boxedPrimitiveArray));
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot unbox primitive array '" + Arrays.toString(boxedPrimitiveArray) + "'", e);
        }
    }

    private DIUtils() {
    }
}
